package com.example.springbootrest.controller;


import com.example.springbootrest.model.Role;
import com.example.springbootrest.model.User;
import com.example.springbootrest.service.interfaces.RoleService;
import com.example.springbootrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class SuppController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public SuppController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String findAll(Model model, Principal principal) {
        model.addAttribute("current_user", userService.findByEmail(principal.getName()));
        return "admin/user-list";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, ModelMap modelMap) {
        modelMap.addAttribute("current_user", userService.findByEmail(principal.getName()));
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {

        if (roleService.findAll().isEmpty()) {
            roleService.add(new Role("ROLE_USER"));
            roleService.add(new Role("ROLE_ADMIN"));

            List<Role> adminRoles = Stream.of(roleService.findByName("ROLE_ADMIN"),
                    roleService.findByName("ROLE_USER")).collect(Collectors.toList());

            User admin = new User("admin", "admin", 22, "admin@mail.com",
                    "admin", adminRoles);
            userService.saveUser(admin);
        }
        return "login";
    }
}
