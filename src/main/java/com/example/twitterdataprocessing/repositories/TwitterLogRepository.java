package com.example.twitterdataprocessing.repositories;

import com.example.twitterdataprocessing.entities.TwitterHashtagCountLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TwitterLogRepository extends JpaRepository<TwitterHashtagCountLog,Long> {
}
