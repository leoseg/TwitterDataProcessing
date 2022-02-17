package com.example.twitterdataprocessing.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterHashtagCount {

    @Getter
    @Setter
    List<TwitterHashtagCountHour> data;

    @Getter
    @Setter
    HashMap<String, Integer> meta;
}
