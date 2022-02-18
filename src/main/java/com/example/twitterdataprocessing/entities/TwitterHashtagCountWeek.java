package com.example.twitterdataprocessing.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Class for mapping the nested json result from Twitter Api Query to java object,
 * maps to a list of hashtagcounts for one week divided by hours in the data list, also the meta map contains
 * the total sum of hashtags over the week
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterHashtagCountWeek {

    @Getter
    @Setter
    List<TwitterHashtagCountHour> data;

    @Getter
    @Setter
    Map<String, Integer> meta;
}
