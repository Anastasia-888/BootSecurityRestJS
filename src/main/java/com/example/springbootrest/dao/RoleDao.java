package com.example.springbootrest.dao;


import com.example.springbootrest.model.Role;

import java.util.List;

public interface RoleDao {
    void add(Role role);

    List<Role> findAll();

    Role getByName(String name);
}
