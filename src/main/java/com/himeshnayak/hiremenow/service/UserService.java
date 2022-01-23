package com.himeshnayak.hiremenow.service;

import com.himeshnayak.hiremenow.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.himeshnayak.hiremenow.model.User;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("cosmosdb") UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
    
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {

        UUID userId = UUID.fromString(id);

        if (userRepository.findByUserId(userId).isEmpty()) 
            return null;
        return userRepository.findByUserId(userId).get(0);
    }

    public User getUserByName(String name) {
        if (userRepository.findByName(name).isEmpty()) 
            return null;
        return userRepository.findByName(name).get(0);
    }

}
