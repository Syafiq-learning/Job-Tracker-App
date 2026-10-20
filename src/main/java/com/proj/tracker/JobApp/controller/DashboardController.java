package com.proj.tracker.JobApp.controller;

import com.proj.tracker.JobApp.model.ApplicationStatus;
import com.proj.tracker.JobApp.model.JobApp;
import com.proj.tracker.JobApp.service.AppService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

// Serves the dashboard page and handles its HTML form actions.
@Controller
public class DashboardController {

    private final AppService appService;

    public DashboardController(AppService appService) {
        this.appService = appService;
    }

    private long count(List<JobApp> apps, ApplicationStatus status) {
        return apps.stream().filter(a -> a.getStatus() == status).count();
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        List<JobApp> apps = appService.getAllApp(principal.getName());
        model.addAttribute("apps", apps);
        model.addAttribute("statuses", ApplicationStatus.values());
        model.addAttribute("total", apps.size());
        model.addAttribute("applied", count(apps, ApplicationStatus.APPLIED));
        model.addAttribute("interview", count(apps, ApplicationStatus.INTERVIEW));
        model.addAttribute("offer", count(apps, ApplicationStatus.OFFER));
        model.addAttribute("rejected", count(apps, ApplicationStatus.REJECTED));
        model.addAttribute("today", LocalDate.now());
        return "dashboard";
    }

    @PostMapping("/applications")
    public String add(Principal principal,
                      @RequestParam String companyName,
                      @RequestParam String position,
                      @RequestParam ApplicationStatus status,
                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appliedDate,
                      @RequestParam(required = false) String notes,
                      RedirectAttributes redirect) {
        if (companyName.isBlank() || position.isBlank()) {
            redirect.addFlashAttribute("error", "Company and position are required.");
            return "redirect:/dashboard";
        }
        JobApp app = new JobApp(companyName.trim(), position.trim(), status, appliedDate);
        app.setNotes(notes);
        appService.createApp(principal.getName(), app);
        return "redirect:/dashboard";
    }

    @PostMapping("/applications/{id}/status")
    public String updateStatus(Principal principal,
                               @PathVariable String id,
                               @RequestParam ApplicationStatus status) {
        JobApp app = appService.getAppByID(principal.getName(), id);
        app.setStatus(status);
        appService.updateApp(principal.getName(), id, app);
        return "redirect:/dashboard";
    }

    @PostMapping("/applications/{id}/delete")
    public String delete(Principal principal, @PathVariable String id) {
        appService.deleteApp(principal.getName(), id);
        return "redirect:/dashboard";
    }
}
