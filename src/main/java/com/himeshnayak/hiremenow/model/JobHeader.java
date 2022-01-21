package com.himeshnayak.hiremenow.model;

public class JobHeader {
    
    private int id;
    private String title;
    private String company;

    public JobHeader(int id, String title, String company) {
        this.id = id;
        this.title = title;
        this.company = company;
    }

    public int getJobId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        String jobDetails = title + " offered by " + company + " has id " + id;
        return jobDetails;
    }

}
