package com.example.springbootrest.service.interfaces;

import com.example.springbootrest.model.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);

    List<Role> findAll();

    Role findByName(String name);

}
