package com.example.job.Repository;

import com.example.job.Entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    @Query("SELECT a FROM Application a WHERE a.applicant.firstName LIKE %:firstName% OR a.applicant.lastName LIKE %:lastName%")
    Page<Application> searchByApplicantName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);
    
    Page<Application> findByApplicantFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<Application> findByApplicantLastNameContainingIgnoreCase(String lastName, Pageable pageable);
    
    Page<Application> findAll(Pageable pageable);
    
    @Query("SELECT a FROM Application a WHERE a.status = :status")
    Page<Application> findByStatus(@Param("status") String status, Pageable pageable);
    
    @Query("SELECT a FROM Application a WHERE a.jobListing.id = :jobListingId ORDER BY a.applicationDate DESC")
    Page<Application> findByJobListingIdOrderByDate(@Param("jobListingId") Long jobListingId, Pageable pageable);
    
    @Query("SELECT a FROM Application a WHERE a.applicant.id = :applicantId ORDER BY a.applicationDate DESC")
    Page<Application> findByApplicantIdOrderByDate(@Param("applicantId") Long applicantId, Pageable pageable);
    
    @Query("SELECT COUNT(a) FROM Application a WHERE a.status = :status")
    long countByStatus(@Param("status") String status);
}
