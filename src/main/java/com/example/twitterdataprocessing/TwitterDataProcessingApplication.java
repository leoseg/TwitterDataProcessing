package com.example.twitterdataprocessing;

import com.example.twitterdataprocessing.kafkaConsumer.TwitterConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class TwitterDataProcessingApplication {

    private final static Logger LOGGER = Logger.getLogger(TwitterDataProcessingApplication.class.getName());
    @Resource
    TwitterConsumer twitterConsumer;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.INFO);
        SpringApplication.run(TwitterDataProcessingApplication.class, args);
    }

}
