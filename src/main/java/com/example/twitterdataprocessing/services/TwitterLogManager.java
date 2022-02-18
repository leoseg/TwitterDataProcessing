package com.example.twitterdataprocessing.services;

import com.example.twitterdataprocessing.entities.TwitterHashtagCountWeek;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountHour;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountLog;
import com.example.twitterdataprocessing.repositories.TwitterLogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class for managing twitter log entries
 */
@Service
public class TwitterLogManager {

    TwitterHashtagCountLog twitterHashtagCountLog;

    @Resource
    TwitterLogRepository twitterLogRepository;

    /**
     * Sets the twitterHashtagCountLog by the data of the last entry of the data list (the most recent) from the record
     * and also passing the name of the topic to the log
     * @param topicname name of the topic the record was from
     * @param record record from the topic of type TwitterHashtagCountWeek
     */
    public void setTwitterHashtagCountLog(String topicname, TwitterHashtagCountWeek record){
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

