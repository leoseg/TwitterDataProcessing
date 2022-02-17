package com.example.twitterdataprocessing.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TwitterHashtagCountHour {


    @Getter
    @Setter
    LocalDateTime end;

    @Getter
    @Setter
    LocalDateTime start;

    @Getter
    @Setter
    int tweet_count;


}
