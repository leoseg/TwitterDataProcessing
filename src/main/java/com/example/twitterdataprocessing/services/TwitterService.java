package com.example.twitterdataprocessing.services;

import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountHour;
import com.example.twitterdataprocessing.entities.TwitterLog;
import com.example.twitterdataprocessing.repositories.TwitterLogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TwitterService {

    TwitterLog twitterLog;

    @Resource
    TwitterLogRepository twitterLogRepository;

    public void setTwitterLog(String topicname, TwitterHashtagCount record){
        List<TwitterHashtagCountHour> data =record.getData();
        TwitterHashtagCountHour newestEntry = data.get(data.size()-1);
        this.twitterLog = new TwitterLog();
        twitterLog.setTimeStamp(newestEntry.getEnd());
        twitterLog.setTweetCount(newestEntry.getTweet_count());
        twitterLog.setName(topicname);
        twitterLog.setTotalTweetCount(record.getMeta().get("total_tweet_cond"));
    }

    public void saveTwitterLog(){
        twitterLogRepository.save(twitterLog);
    }
}
