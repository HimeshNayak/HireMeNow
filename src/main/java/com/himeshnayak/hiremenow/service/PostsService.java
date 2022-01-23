package com.himeshnayak.hiremenow.service;

import com.himeshnayak.hiremenow.repository.PostsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.himeshnayak.hiremenow.model.PostDetails;

import java.util.List;

@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(@Qualifier("cosmosdbPosts") PostsRepository postsRepository){
        this.postsRepository = postsRepository;
    }

    public void addPost(PostDetails post) {
        postsRepository.save(post);
    }
    
    public List<PostDetails> getPosts() {
        return postsRepository.findAll();
    }

}
