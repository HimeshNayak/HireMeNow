package com.himeshnayak.hiremenow.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.himeshnayak.hiremenow.data.Job;

public interface JobRepository extends MongoRepository <Job, String> {}
