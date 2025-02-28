package com.example.job.Service;

import com.example.job.Entity.JobListing;
import com.example.job.Exception.ResourceNotFoundException;
import com.example.job.Repository.JobListingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobListingServiceImpl implements JobListingService {


    private final JobListingRepository jobListingRepository;

    public JobListingServiceImpl(JobListingRepository jobListingRepository) {
        this.jobListingRepository = jobListingRepository;
    }

    @Override
    public JobListing createJobListing(JobListing jobListing) {
        return jobListingRepository.save(jobListing);
    }

    @Override
    public JobListing getJobListingById(Long id) {
        return jobListingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job Listing not found with id: " + id));
    }

    @Override
    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

    @Override
    public Page<JobListing> getAllJobListings(Pageable pageable) {
        return jobListingRepository.findAll(pageable);
    }

    @Override
    public Page<JobListing> searchByTitle(String title, Pageable pageable) {
        return jobListingRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<JobListing> searchByLocation(String location, Pageable pageable) {
        return jobListingRepository.findByLocationContainingIgnoreCase(location, pageable);
    }

    @Override
    public Page<JobListing> searchBySalaryRange(double minSalary, double maxSalary, Pageable pageable) {
        return jobListingRepository.findBySalaryRange(minSalary, maxSalary, pageable);
    }

    @Override
    public Page<JobListing> searchByJobType(String jobType, Pageable pageable) {
        return jobListingRepository.findByJobTypeOrderByPostedDateDesc(jobType, pageable);
    }

    @Override
    public JobListing updateJobListing(Long id, JobListing jobListing) {
        JobListing existingJobListing = getJobListingById(id);
        existingJobListing.setTitle(jobListing.getTitle());
        existingJobListing.setDescription(jobListing.getDescription());
        existingJobListing.setLocation(jobListing.getLocation());
        existingJobListing.setSalary(jobListing.getSalary());
        existingJobListing.setJobType(jobListing.getJobType());
        existingJobListing.setPostedDate(jobListing.getPostedDate());
        existingJobListing.setExpirationDate(jobListing.getExpirationDate());
        existingJobListing.setActive(jobListing.isActive());
        return jobListingRepository.save(existingJobListing);
    }

    @Override
    public void deleteJobListing(Long id) {
        jobListingRepository.deleteById(id);
    }
}
