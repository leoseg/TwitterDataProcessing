package com.example.twitterdataprocessing.kafkaConfig;

import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfigTwitter {

    @Value("${kafka.adress}")
    private String bootstrapAddress;

    @Value("group-1")
    private String groupId;

    /**
     * Creates consumerfactory for creating consumer instances that deserialize
     * payloads from messages of PredictIt-topic from kafka brooker
     *
     * @return consumferfactory
     */
    @Bean
    @Lazy
    public ConsumerFactory<String, TwitterHashtagCount> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        JsonDeserializer<TwitterHashtagCount> deserializer = new JsonDeserializer<>(TwitterHashtagCount.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    /**
     * Creates listener container for each method annotated with @kafkalistener
     * for handling the connection to the kafka broker
     * provide multi threaded consumption
     *
     * @return listenercontainerfactory
     */
    @Bean(value = "twitterListener")
    @Lazy
    public ConcurrentKafkaListenerContainerFactory<String, TwitterHashtagCount>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, TwitterHashtagCount> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(true);
        return factory;

    }
}