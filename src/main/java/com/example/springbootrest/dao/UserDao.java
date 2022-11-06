package com.example.springbootrest.dao;


import com.example.springbootrest.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void add(User user);

    User getUserById(long id);

    void delete(long id);

    void update(User newUser, long id);

    User getUserByEmail(String email);
}
