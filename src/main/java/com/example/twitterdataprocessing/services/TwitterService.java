package com.example.twitterdataprocessing.services;

import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountHour;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountLog;
import com.example.twitterdataprocessing.repositories.TwitterLogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TwitterService {

    TwitterHashtagCountLog twitterHashtagCountLog;

    @Resource
    TwitterLogRepository twitterLogRepository;

    public void setTwitterHashtagCountLog(String topicname, TwitterHashtagCount record){
        List<TwitterHashtagCountHour> data =record.getData();
        TwitterHashtagCountHour newestEntry = data.get(data.size()-1);
        this.twitterHashtagCountLog = new TwitterHashtagCountLog();
        twitterHashtagCountLog.setTimeStamp(newestEntry.getEnd());
        twitterHashtagCountLog.setTweetCount(newestEntry.getTweet_count());
        twitterHashtagCountLog.setName(topicname);
        twitterHashtagCountLog.setTotalTweetCount(record.getMeta().get("total_tweet_count"));
    }

    public void saveTwitterHashtagCountLog(){
        twitterLogRepository.save(twitterHashtagCountLog);
    }
}

