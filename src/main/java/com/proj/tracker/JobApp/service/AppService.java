package com.proj.tracker.JobApp.service;

import com.proj.tracker.JobApp.model.JobApp;
import java.util.List;

public interface AppService {

    JobApp createApp(String email, JobApp app);

    List<JobApp> getAllApp(String email);

    JobApp getAppByID(String email, String id);

    JobApp updateApp(String email, String id, JobApp app);

    void deleteApp(String email, String id);
}
