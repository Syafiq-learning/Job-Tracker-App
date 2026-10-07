package com.proj.tracker.User.service;

import java.util.List;

import com.proj.tracker.User.dto.RegisterRequest;
import com.proj.tracker.User.model.User;

public interface UserService {


    List<User> getAllUsers();

    User getUserById(String id);

    User updateUser(String id, User user);

    User register(RegisterRequest request);

    void deleteUser(String id);

}
