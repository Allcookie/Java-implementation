package com.example.springproject.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private String coverImage;
    private String biography;
    private String phoneNumber;
}