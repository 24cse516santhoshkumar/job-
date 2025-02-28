package com.example.job.Controller;

import com.example.job.Entity.JobListing;
import com.example.job.Service.JobListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-listings")
public class JobListingController {

    private final JobListingService jobListingService;

    public JobListingController(JobListingService jobListingService) {
        this.jobListingService = jobListingService;
    }

    @PostMapping
    public ResponseEntity<JobListing> createJobListing(@RequestBody JobListing jobListing) {
        JobListing createdJobListing = jobListingService.createJobListing(jobListing);
        return new ResponseEntity<>(createdJobListing, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobListing> getJobListingById(@PathVariable Long id) {
        JobListing jobListing = jobListingService.getJobListingById(id);
        return ResponseEntity.ok(jobListing);
    }

    @GetMapping
    public ResponseEntity<List<JobListing>> getAllJobListings() {
        List<JobListing> jobListings = jobListingService.getAllJobListings();
        return ResponseEntity.ok(jobListings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobListing> updateJobListing(
            @PathVariable Long id, @RequestBody JobListing jobListing) {
        JobListing updatedJobListing = jobListingService.updateJobListing(id, jobListing);
        return ResponseEntity.ok(updatedJobListing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobListing(@PathVariable Long id) {
        jobListingService.deleteJobListing(id);
        return ResponseEntity.noContent().build();
    }
}
