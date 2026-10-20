package com.proj.tracker.JobApp.service;

import com.proj.tracker.JobApp.model.JobApp;
import com.proj.tracker.JobApp.repository.AppRepo;
import com.proj.tracker.User.repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppImpl implements AppService{

    private final AppRepo appRepo;
    private final UserRepo userRepo;

    public AppImpl(AppRepo appRepo, UserRepo userRepo){
        this.appRepo = appRepo;
        this.userRepo = userRepo;
    }

    // The logged-in user is identified by email; jobs are stored with that user's id.
    private String userIdOf(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"))
                .getId();
    }

    private JobApp findOwned(String email, String id) {
        return appRepo.findByIdAndUserId(id, userIdOf(email))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    @Override
    public JobApp createApp(String email, JobApp app){
        app.setUserId(userIdOf(email));
        return appRepo.save(app);
    }

    @Override
    public List<JobApp> getAllApp(String email) {
        return appRepo.findByUserIdOrderByAppliedDateDesc(userIdOf(email));
    }

    @Override
    public JobApp getAppByID(String email, String id) {
        return findOwned(email, id);
    }

    @Override
    public JobApp updateApp(String email, String id, JobApp app) {
        JobApp existingApp = findOwned(email, id);

        existingApp.setCompanyName(app.getCompanyName());
        existingApp.setPosition(app.getPosition());
        existingApp.setStatus(app.getStatus());
        existingApp.setAppliedDate(app.getAppliedDate());
        existingApp.setNotes(app.getNotes());

        return appRepo.save(existingApp);
    }

    @Override
    public void deleteApp(String email, String id) {
        appRepo.delete(findOwned(email, id));
    }
}
