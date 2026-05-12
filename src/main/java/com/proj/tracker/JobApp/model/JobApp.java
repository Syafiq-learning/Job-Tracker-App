package com.proj.tracker.JobApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "applications")
public class JobApp {



    @Id
    private String id;

    private String userId;

    private String companyName;

    private String position;

    private ApplicationStatus status;

    private LocalDate appliedDate;

    private String notes;
}
