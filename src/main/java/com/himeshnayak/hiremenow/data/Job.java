package com.himeshnayak.hiremenow.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

	@Id
	private String id;

	private String name;

	public Job(String id, String name) {
		this.id = id;
		this.name = name;
	}

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}