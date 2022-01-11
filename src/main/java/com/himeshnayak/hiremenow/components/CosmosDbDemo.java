package com.himeshnayak.hiremenow.components;

import org.springframework.stereotype.Component;

import com.himeshnayak.hiremenow.repository.JobRepository;

@Component
public class CosmosDbDemo {

	CosmosDbDemo(JobRepository rr) {
		// rr.save(new Job(null, "Himesh Nayak"));
		rr.findAll().forEach(job -> {
            String id = job.getId();
            String name = job.getName();
            System.out.println(id + " : " + name);
        });
	}

}