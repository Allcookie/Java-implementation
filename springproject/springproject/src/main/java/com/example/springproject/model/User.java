package com.example.springproject.model;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String userName;
    private String email;
    private String passwordHash;
    private String salt;
    private String coverImage;
    private String biography;
}
