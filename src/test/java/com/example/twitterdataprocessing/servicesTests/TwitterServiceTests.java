package com.example.twitterdataprocessing.servicesTests;


import com.example.twitterdataprocessing.entities.TwitterHashtagCount;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountHour;
import com.example.twitterdataprocessing.entities.TwitterHashtagCountLog;
import com.example.twitterdataprocessing.repositories.TwitterLogRepository;
import com.example.twitterdataprocessing.services.TwitterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TwitterServiceTests {

    @Resource
    TwitterLogRepository twitterLogRepository;

    @Resource
    TwitterService twitterService;

    TwitterHashtagCount testTwitterHashtagCount;

    @BeforeEach
    void setTestTwitterHashtagCount(){
        testTwitterHashtagCount = new TwitterHashtagCount();
        testTwitterHashtagCount.setMeta(Map.of("total_tweet_count",1000));
        TwitterHashtagCountHour twitterHashtagCount1 = new TwitterHashtagCountHour();
        twitterHashtagCount1.setTweet_count(400);
        twitterHashtagCount1.setEnd(LocalDateTime.of(2022,1,22,14,30));
        twitterHashtagCount1.setStart(LocalDateTime.of(2022,1,22,13,30));
        TwitterHashtagCountHour twitterHashtagCount2 = new TwitterHashtagCountHour();
        twitterHashtagCount2.setTweet_count(600);
        twitterHashtagCount2.setEnd(LocalDateTime.of(2022,1,22,15,30));
        twitterHashtagCount2.setStart(LocalDateTime.of(2022,1,22,14,30));
        testTwitterHashtagCount.setData(Arrays.asList(twitterHashtagCount1,twitterHashtagCount2));
    }

    @Test
    void givenTwitterHashtagCount_whenTwitterSerivceSaveTwitterHashtagCountLog_thenTwitterHashtagCountLogFoundByRepositoryShouldHaveExspectedData(){
        twitterService.setTwitterHashtagCountLog("testtopic",testTwitterHashtagCount);
        twitterService.saveTwitterHashtagCountLog();
        List<TwitterHashtagCountLog> twitterHashtagCountLogList= twitterLogRepository.findAll();
        TwitterHashtagCountLog actualTwitterHashtagCountLog = twitterHashtagCountLogList.get(0);
        assert (actualTwitterHashtagCountLog.getTweetCount() ==600);
        assert (actualTwitterHashtagCountLog.getTotalTweetCount()==1000);
        assert (Objects.equals(actualTwitterHashtagCountLog.getName(), "testtopic"));
        assert (Objects.equals(actualTwitterHashtagCountLog.getTimeStamp(), LocalDateTime.of(2022, 1, 22, 15, 30)));


    }

}
