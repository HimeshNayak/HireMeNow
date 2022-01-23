package com.himeshnayak.hiremenow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.himeshnayak.hiremenow.model.User;

import java.util.List;
import java.util.UUID;

@Repository("cosmosdb")
public interface UserRepository extends MongoRepository<User, String>{

    public List<User> findByName(String name);

    public List<User> findByType(String type);

    public List<User> findByUserId(UUID userId);
    
}
