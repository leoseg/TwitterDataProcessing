package com.example.twitterdataprocessing.kafkaConsumerTests;

import com.example.twitterdataprocessing.entities.TwitterHashtagCountWeek;
import com.example.twitterdataprocessing.helpClasses.JsonDataBuilder;
import com.example.twitterdataprocessing.kafkaConsumer.TwitterConsumer;
import com.example.twitterdataprocessing.services.TwitterLogManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@RunWith(SpringRunner.class)
@EmbeddedKafka(partitions = 1,bootstrapServersProperty = "localhost:9092"
        ,brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092", "num-partitions=1" }
        ,topics={"twittercount.test"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TwitterConsumerTests {

    @MockBean
    TwitterLogManager twitterLogManager;

    @Resource
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Resource
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @BeforeAll
    public void setUp()  {
        for (final MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
            ContainerTestUtils.waitForAssignment(messageListenerContainer,
                    1);
        }
    }

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @Resource
    private TwitterConsumer twitterConsumer;

    private TwitterHashtagCountWeek testTwitterHashtagCountWeek;


    @Test
    public void givenSixHoursOfTwitterHashtagCounts_whenKafaListenerIsActive_thenOnlyTheLastHourShouldBeSaved() throws InterruptedException, ExecutionException {
        JsonDataBuilder jsonDataBuilder = new JsonDataBuilder();
        int tweet_count = 350;
        int numberHours = 6;
        jsonDataBuilder.buildTwitterHashtagCountJson(numberHours, tweet_count);
        ArgumentCaptor<TwitterHashtagCountWeek> argumentCaptor = ArgumentCaptor.forClass(TwitterHashtagCountWeek.class);
        kafkaTemplate.send("twittercount.test",jsonDataBuilder.getJsonStringOfObject());
        Thread.sleep(5000);

        verify(twitterLogManager,times(1)).saveTwitterHashtagCountLog();
        verify(twitterLogManager,times(1)).setTwitterHashtagCountLog(eq("twittercount.test"),argumentCaptor.capture());
        assert(argumentCaptor.getValue().getData().get(0).getTweet_count()==200);
        assert(argumentCaptor.getValue().getData().get(numberHours ).getTweet_count()==350);
        assert(Objects.equals(argumentCaptor.getValue().getData().get(numberHours ).getEnd(), LocalDateTime.of(2022, 2, 17, 16, 0)));
    }


}
