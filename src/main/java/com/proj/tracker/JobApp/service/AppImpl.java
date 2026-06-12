package com.proj.tracker.JobApp.service;

import com.proj.tracker.JobApp.model.JobApp;
import com.proj.tracker.JobApp.repository.AppRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppImpl implements AppService{

    private final AppRepo appRepo;

    public AppImpl(AppRepo appRepo){
        this.appRepo = appRepo;
    }

    @Override
    public JobApp createApp(JobApp app){
        return appRepo.save(app);
    }

    @Override
    public List<JobApp> getAllApp() {
        return appRepo.findAll();
    }

    @Override
    public JobApp getAppByID(String id) {
        return appRepo.findById(id).orElse(null);
    }

    @Override
    public JobApp updateApp(String id, JobApp app) {

        JobApp existingApp = appRepo.findById(id).orElse(null);

        if(existingApp == null){
            return null;
        }

        existingApp.setCompanyName(app.getCompanyName());

        existingApp.setPosition(app.getPosition());

        existingApp.setStatus(app.getStatus());

        existingApp.setAppliedDate(app.getAppliedDate());

        return appRepo.save(existingApp);

    }

    @Override
    public void deleteApp(String id) {
        appRepo.deleteById(id);
    }


}
