package com.proj.tracker.User.service;

import java.util.List;
import com.proj.tracker.User.model.User;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    User updateUser(String id, User user);

    void deleteUser(String id);

}
