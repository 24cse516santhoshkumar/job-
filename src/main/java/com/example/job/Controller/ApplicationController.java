package com.example.job.Controller;

import com.example.job.Entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.job.Service.ApplicationService;
import org.springframework.web.bind.annotation.*;  
import org.springframework.http.ResponseEntity;  

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    
    @GetMapping("/search")
    public Page<Application> searchApplicationsByApplicantName(@RequestParam String firstName, @RequestParam String lastName, Pageable pageable) {
        return applicationService.searchByApplicantName(firstName, lastName, pageable);
    }


    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationService.createApplication(application);
    }

    @PutMapping("/{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody Application application) {
        return applicationService.updateApplication(id, application);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted successfully");
    }
}
