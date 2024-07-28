package com.mmk.library.service;

import com.mmk.library.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    void disableUserById(Integer userId);

    List<User> searchByKeyword(String keyWord);
}
