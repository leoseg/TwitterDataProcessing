package com.example.twitterdataprocessing;

import com.example.twitterdataprocessing.kafkaConsumer.TwitterConsumer;
import com.example.twitterdataprocessing.repositories.TwitterLogRepository;
import com.example.twitterdataprocessing.services.TwitterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TwitterDataProcessingApplicationTests {


    @Resource
    TwitterService twitterService;

    @Resource
    TwitterConsumer twitterConsumer;

    @Resource
    TwitterLogRepository twitterLogRepository;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(twitterConsumer);
        Assertions.assertNotNull(twitterService);
        Assertions.assertNotNull(twitterLogRepository);
    }

}
