package com.example.springproject.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
