package com.example.twitterdataprocessing.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Class for mapping the results of Twitter api query, maps to an entry of the count of hashtags of one hour
 * from start to end
 */
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
