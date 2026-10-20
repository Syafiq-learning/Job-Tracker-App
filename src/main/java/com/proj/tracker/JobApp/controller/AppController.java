package com.proj.tracker.JobApp.controller;

import com.proj.tracker.JobApp.model.JobApp;
import com.proj.tracker.JobApp.service.AppService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping
    public JobApp createApp(Principal principal,
                            @Validated @RequestBody JobApp app){
        return appService.createApp(principal.getName(), app);
    }

    @GetMapping
    public List<JobApp> getAllApp(Principal principal){
        return appService.getAllApp(principal.getName());
    }

    @GetMapping("/{id}")
    public JobApp getAppByID(Principal principal, @PathVariable String id){
        return appService.getAppByID(principal.getName(), id);
    }

    @PutMapping("/{id}")
    public JobApp updateApp(Principal principal,
                            @PathVariable String id,
                            @Validated @RequestBody JobApp app){
        return appService.updateApp(principal.getName(), id, app);
    }

    @DeleteMapping("/{id}")
    public void deleteApp(Principal principal, @PathVariable String id){
        appService.deleteApp(principal.getName(), id);
    }
}
