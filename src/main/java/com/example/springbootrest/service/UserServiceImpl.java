package com.example.springbootrest.service;

import com.example.springbootrest.dao.UserDao;
import com.example.springbootrest.model.User;
import com.example.springbootrest.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.add(user);
    }


    @Override
    @Transactional
    public User findById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public void update(User user, long id) {
        userRepository.update(user, id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

}
