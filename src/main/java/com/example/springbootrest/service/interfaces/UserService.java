package com.example.springbootrest.service.interfaces;


import com.example.springbootrest.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> findAll();

    User findById(long id);

    void update(User user, long id);

    void deleteById(long id);

    User findByEmail(String email);

}
