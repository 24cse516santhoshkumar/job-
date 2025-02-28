package com.example.job.Repository;

import com.example.job.Entity.JobListing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    
    Page<JobListing> findAll(Pageable pageable);
    
    Page<JobListing> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    Page<JobListing> findByLocationContainingIgnoreCase(String location, Pageable pageable);
    
    @Query("SELECT j FROM JobListing j WHERE j.salary >= :minSalary AND j.salary <= :maxSalary")
    Page<JobListing> findBySalaryRange(@Param("minSalary") double minSalary, 
                                      @Param("maxSalary") double maxSalary, 
                                      Pageable pageable);
                                      
    @Query("SELECT j FROM JobListing j WHERE j.jobType = :jobType ORDER BY j.postedDate DESC")
    Page<JobListing> findByJobTypeOrderByPostedDateDesc(@Param("jobType") String jobType, 
                                                      Pageable pageable);
}
