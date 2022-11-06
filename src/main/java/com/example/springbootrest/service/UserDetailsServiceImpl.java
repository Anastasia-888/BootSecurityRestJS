package com.example.springbootrest.service;

import com.example.springbootrest.dao.UserDao;
import com.example.springbootrest.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userRepository;

    public UserDetailsServiceImpl(UserDao userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(s);

        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getAuthorities());
    }
}
