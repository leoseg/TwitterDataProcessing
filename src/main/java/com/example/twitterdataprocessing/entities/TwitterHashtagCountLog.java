package com.example.twitterdataprocessing.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Defines a log entity for the hashtag count with the corresponding timestamp
 * the tweetCount and the name of the topic the log was obtained from, and also the total tweetcount for the last
 * 7 days for that hashtag
 */
@Entity
public class TwitterHashtagCountLog {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    int tweetCount;

    @Getter
    @Setter
    LocalDateTime timeStamp;

    @Getter
    @Setter
    int totalTweetCount;

    @Getter
    @Setter
    String name;
}
