package com.example.twitterdataprocessing.helpClasses;

import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ArrayNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Class for building json data for testing
 */
public class JsonDataBuilder {

    ObjectNode jsonObject;
    ObjectMapper objectMapper;

    /**
     * Constructor that initalize the object mapper attribute
     */
    public JsonDataBuilder(){
       this.objectMapper = new ObjectMapper();
    }


    public void buildTwitterHashtagCountJson(int numberHours,int tweet_count){
        ObjectNode twitterHashtagCount = objectMapper.createObjectNode();
        ObjectNode meta = objectMapper.createObjectNode();
        meta.put("total_tweet_count",numberHours*200+tweet_count);
        twitterHashtagCount.set("meta",meta);
        ArrayNode data= objectMapper.createArrayNode();
        for(int i=0; i<numberHours;i++){
            data.add(buildHashtagCountHourNode());
        }
        data.add(buildLastNode(tweet_count));
        twitterHashtagCount.putArray("data").addAll(data);
        this.jsonObject = twitterHashtagCount;
    }

    private ObjectNode buildLastNode(int tweet_count) {
        ObjectNode hashtagCountHourNode = objectMapper.createObjectNode();
        hashtagCountHourNode.put("tweet_count",tweet_count);
        hashtagCountHourNode.put("start","2022-02-17T15:00:00.000Z");
        hashtagCountHourNode.put("end","2022-02-17T16:00:00.000Z");
        return hashtagCountHourNode;
    }


    private ObjectNode buildHashtagCountHourNode(){
        ObjectNode hashtagCountHourNode = objectMapper.createObjectNode();
        hashtagCountHourNode.put("tweet_count",200);
        hashtagCountHourNode.put("start","2022-02-17T14:00:00.000Z");
        hashtagCountHourNode.put("end","2022-02-17T15:00:00.000Z");
        return hashtagCountHourNode;
    }


    /*
    Returns the json object build before as string
     */
    public String getJsonStringOfObject(){
        return this.jsonObject.toString();
    }
}
