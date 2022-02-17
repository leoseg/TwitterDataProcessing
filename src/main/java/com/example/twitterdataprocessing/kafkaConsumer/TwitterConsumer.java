package com.example.twitterdataprocessing.kafkaConsumer;

import com.example.twitterdataprocessing.TwitterDataProcessingApplication;
import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import com.example.twitterdataprocessing.services.TwitterService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TwitterConsumer {

    private final static Logger LOGGER = Logger.getLogger(TwitterDataProcessingApplication.class.getName());


    @Resource
    TwitterService twitterService;




    @KafkaListener(topicPattern= "twittercount.*", groupId = "group-1", containerFactory = "twitterListener")
    public void listenTwitterCountTopic(TwitterHashtagCount record,@Header(KafkaHeaders.RECEIVED_TOPIC) String topicname) {
        LOGGER.log(Level.INFO, "Recieved message from "+topicname+"  at "+LocalDateTime.now());
        twitterService.setTwitterHashtagCountLog(topicname,record);
        twitterService.saveTwitterHashtagCountLog();
    }
}

