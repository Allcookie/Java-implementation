package com.example.springproject.model;

// import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Post {
    private Integer postId;
    private Integer userId;
    private String content;
    private String image;
    private OffsetDateTime  createdAt;
}
