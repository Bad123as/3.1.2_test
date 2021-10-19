package com.example.springboot.service;

import com.example.springboot.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(User user);
    Object getUserById(long id);
    List<User> getAllUsers();
}
