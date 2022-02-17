package com.example.twitterdataprocessing.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterHashtagCount {

    @Getter
    @Setter
    List<TwitterHashtagCountHour> data;

    @Getter
    @Setter
    Map<String, Integer> meta;
}
