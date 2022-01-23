package com.himeshnayak.hiremenow.model;

public class PostDetails {
    
    private String userId;
    private String name;
    private String type;
    private String note;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getNote() {
        return note;
    }

    public String toString() {
        return note + " by " + name;
    }

}
