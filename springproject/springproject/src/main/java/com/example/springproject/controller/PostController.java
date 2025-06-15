package com.example.springproject.controller;

import com.example.springproject.model.Post;
import com.example.springproject.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    // Postman發布文章時Headers要加入Authorization內容"Bearer 登入產生的token"
    @PostMapping("/posts")
    public String createPost(@RequestBody Post post) {
        postService.createPost(post);
        System.out.println("文章發布成功！");
        return "Post created successfully!";
    }

    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }
}
