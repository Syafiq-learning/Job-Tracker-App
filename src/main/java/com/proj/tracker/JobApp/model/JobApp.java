package com.proj.tracker.JobApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "job")
public class JobApp {


    @Id
    private String id;

    private String userId;
    private String companyName;
    private String position;
    private ApplicationStatus status;
    private LocalDate appliedDate;
    private String notes;

    public JobApp(String companyName, String position, ApplicationStatus status, LocalDate appliedDate) {
        this.companyName = companyName;
        this.position = position;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    @Override
    public String toString() {
        return "JobApp{" +
                "notes='" + notes + '\'' +
                '}';
    }
}
