package com.example.twitterdataprocessing.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
