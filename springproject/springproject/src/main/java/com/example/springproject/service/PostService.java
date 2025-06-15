package com.example.springproject.service;

import com.example.springproject.model.Post;
import com.example.springproject.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 新增貼文
    public void createPost(Post post) {
        postRepository.createPost(post);
    }

    // 查詢所有貼文
    public List<Post> getAllPosts() {
        return postRepository.findAllPosts();
    }
}
