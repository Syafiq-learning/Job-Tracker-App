package com.proj.tracker.JobApp.repository;

import com.proj.tracker.JobApp.model.JobApp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRepo extends MongoRepository<JobApp,String>{
}
