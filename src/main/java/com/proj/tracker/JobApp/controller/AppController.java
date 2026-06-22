package com.proj.tracker.JobApp.controller;

import com.proj.tracker.JobApp.model.JobApp;
import com.proj.tracker.JobApp.service.AppService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public JobApp createApp(
           @Validated @RequestBody JobApp app){
        return appService.createApp(app);
    }

    @GetMapping
    public List<JobApp> getAllApp(){
        return appService.getAllApp();
    }

    @GetMapping("/{id}")
    public JobApp getAppByID(@PathVariable String id){
        return appService.getAppByID(id);
    }

    @PutMapping("/{id}")
    public JobApp updateApp(@PathVariable String id,
                            @RequestBody JobApp app){
        return appService.updateApp(id,app);
    }

    @DeleteMapping("/{id}")
    public void deleteApp(@PathVariable String id){
        appService.deleteApp(id);
    }
}









