package com.example.job.Service;

import com.example.job.Entity.JobListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface JobListingService {
    JobListing createJobListing(JobListing jobListing);
    JobListing getJobListingById(Long id);
    List<JobListing> getAllJobListings();
    Page<JobListing> getAllJobListings(Pageable pageable);
    Page<JobListing> searchByTitle(String title, Pageable pageable);
    Page<JobListing> searchByLocation(String location, Pageable pageable);
    Page<JobListing> searchBySalaryRange(double minSalary, double maxSalary, Pageable pageable);
    Page<JobListing> searchByJobType(String jobType, Pageable pageable);
    JobListing updateJobListing(Long id, JobListing jobListing);
    void deleteJobListing(Long id);
}
