package com.example.twitterdataprocessing.kafkaConsumer;

import com.example.twitterdataprocessing.TwitterDataProcessingApplication;
import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountHour;
import com.example.twitterdataprocessing.entities.TwitterLog;
import com.example.twitterdataprocessing.services.TwitterService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TwitterConsumer {

    private final static Logger LOGGER = Logger.getLogger(TwitterDataProcessingApplication.class.getName());


    @Resource
    TwitterService twitterService;



    /**
     * Method for consuming all topics beginning with president.* and saving it to the to the same table
     * @param record record of type predictittopic with the data
     */
    @KafkaListener(topicPattern= "twitter_count.*", groupId = "group-1", containerFactory = "twitterListener")
    public void listenTwitterCountTopic(TwitterHashtagCount record,@Header(KafkaHeaders.RECEIVED_TOPIC) String topicname) {
        LOGGER.log(Level.INFO, "Recieved message from "+topicname+"  at "+LocalDateTime.now());
        twitterService.setTwitterLog(topicname,record);
        twitterService.saveTwitterLog();
    }
}

