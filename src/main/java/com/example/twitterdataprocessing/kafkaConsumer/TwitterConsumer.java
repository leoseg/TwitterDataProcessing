package com.example.twitterdataprocessing.kafkaConsumer;

import com.example.twitterdataprocessing.TwitterDataProcessingApplication;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountWeek;
import com.example.twitterdataprocessing.services.TwitterLogManager;
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
    TwitterLogManager twitterLogManager;


    /**
     * Method for consuming all topics beginning with twittercount.* and saving it to the to the same table.
     * The * refers to the name of the candidate for which the hashtags were counted
     * @param record record record of type TwitterHashtagCountWeek
     * @param topicname name of the topic which is stored with the log
     */
    @KafkaListener(topicPattern= "twittercount.*", groupId = "group-1", containerFactory = "twitterListener")
    public void listenTwitterCountTopic(TwitterHashtagCountWeek record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topicname) {
        LOGGER.log(Level.INFO, "Recieved message from "+topicname+"  at "+LocalDateTime.now());
        twitterLogManager.setTwitterHashtagCountLog(topicname,record);
        twitterLogManager.saveTwitterHashtagCountLog();
    }
}

