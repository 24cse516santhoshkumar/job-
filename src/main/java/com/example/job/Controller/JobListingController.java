package com.example.job.Controller;

import com.example.job.Entity.JobListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.job.Service.JobListingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<JobListing>> getAllJobListings(Pageable pageable) {
        Page<JobListing> jobListings = jobListingService.getAllJobListings(pageable);
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Page<JobListing>> searchByTitle(@RequestParam String title, Pageable pageable) {
        Page<JobListing> jobListings = jobListingService.searchByTitle(title, pageable);
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/search/location")
    public ResponseEntity<Page<JobListing>> searchByLocation(@RequestParam String location, Pageable pageable) {
        Page<JobListing> jobListings = jobListingService.searchByLocation(location, pageable);
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/search/salary")
    public ResponseEntity<Page<JobListing>> searchBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary, Pageable pageable) {
        Page<JobListing> jobListings = jobListingService.searchBySalaryRange(minSalary, maxSalary, pageable);
        return ResponseEntity.ok(jobListings);
    }

    @GetMapping("/search/type")
    public ResponseEntity<Page<JobListing>> searchByJobType(@RequestParam String jobType, Pageable pageable) {
        Page<JobListing> jobListings = jobListingService.searchByJobType(jobType, pageable);
        return ResponseEntity.ok(jobListings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobListing> updateJobListing(
            @PathVariable Long id, @RequestBody JobListing jobListing) {
        JobListing updatedJobListing = jobListingService.updateJobListing(id, jobListing);
        return ResponseEntity.ok(updatedJobListing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobListing(@PathVariable Long id) {
        jobListingService.deleteJobListing(id);
        return ResponseEntity.ok("{\"message\": \"Job listing deleted successfully.\"}");
    }
}
