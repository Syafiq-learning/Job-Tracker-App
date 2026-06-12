package com.proj.tracker.JobApp.service;

import com.proj.tracker.JobApp.model.JobApp;
import java.util.List;

public interface AppService {

    JobApp createApp(JobApp app);

    List<JobApp> getAllApp();

    JobApp getAppByID(String id);

    JobApp updateApp(String id, JobApp app);

    void deleteApp(String id);
}
