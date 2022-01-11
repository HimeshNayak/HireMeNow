package com.himeshnayak.hiremenow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.himeshnayak.hiremenow.model.User;

@Repository("cosmosdb")
public interface UserRepository extends MongoRepository<User, String>{
    
}
