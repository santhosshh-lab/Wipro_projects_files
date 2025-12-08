package com.adminservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adminservice.dto.UserDto;
import com.adminservice.entity.Admin;
import com.adminservice.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/register")
    public Admin register(@RequestBody Admin a) {
        return service.register(a);
    }

    @PostMapping("/login")
    public Admin login(@RequestBody Admin req) {
        return service.login(req.getEmail(), req.getPassword());
    }
    
    // CREATE USER
    @PostMapping("/users")
    public UserDto addUser(@RequestBody UserDto u) {
        return service.createUser(u);
    }

    // GET ALL USERS
    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // UPDATE USER
    @PutMapping("/users/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto u) {
        return service.updateUser(id, u);
    }

    // DELETE USER
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

}
