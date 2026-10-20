package com.proj.tracker.JobApp.repository;

import com.proj.tracker.JobApp.model.JobApp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppRepo extends MongoRepository<JobApp,String>{

    List<JobApp> findByUserIdOrderByAppliedDateDesc(String userId);

    Optional<JobApp> findByIdAndUserId(String id, String userId);
}
