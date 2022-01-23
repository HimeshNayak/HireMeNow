package com.himeshnayak.hiremenow.model;

import java.util.UUID;

public class User {
    
    private UUID userId;
    private String name;
    private String password;
    private String type;

    public void setUserId() {
        this.userId = UUID.randomUUID();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getUUID() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "UUID: " + userId + "\nName: " + name + "\npassword: " + password + "\nType: " + type;
    }

}
