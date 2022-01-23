package com.himeshnayak.hiremenow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.himeshnayak.hiremenow.model.PostDetails;

import java.util.List;

@Repository("cosmosdbPosts")
public interface PostsRepository extends MongoRepository<PostDetails, String>{

    public List<PostDetails> findByType(String type);
    
}