package com.proj.tracker.User.repository;

import com.proj.tracker.User.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
