package com.example.springproject.controller;

import com.example.springproject.model.RegisterRequest;
import com.example.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        System.out.println("收到註冊資料：" + request.getUserName() + ", " + request.getEmail() + ", " + request.getPassword());
        userService.register(request.getUserName(), request.getEmail(), request.getPassword(), request.getCoverImage(), request.getBiography());
        return "User registered successfully!";
    }
}