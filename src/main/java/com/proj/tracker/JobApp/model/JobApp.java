package com.proj.tracker.JobApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Document(collection = "job")
public class JobApp {


    @Id
    private String id;

    private String userId;
    @NotBlank
    private String companyName;
    @NotBlank
    private String position;
    @NotNull
    private ApplicationStatus status;
    private LocalDate appliedDate;
    private String notes;

    public JobApp(String companyName, String position, ApplicationStatus status, LocalDate appliedDate) {
        this.companyName = companyName;
        this.position = position;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public String getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", status=" + status +
                '}';
    }
}
