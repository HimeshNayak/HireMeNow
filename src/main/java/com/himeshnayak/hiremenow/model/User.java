package com.himeshnayak.hiremenow.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    
    private final UUID id;
    private final String name;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name) {
        if (id == null)
            this.id = UUID.randomUUID();
        else 
            this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
