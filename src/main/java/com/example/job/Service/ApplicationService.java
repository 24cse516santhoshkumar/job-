package com.example.job.Service;

import com.example.job.Entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ApplicationService {
    
    Page<Application> searchByApplicantName(String firstName, String lastName, Pageable pageable);
    Application createApplication(Application application);
    Application getApplicationById(Long id);
    List<Application> getAllApplications();
    Page<Application> getAllApplications(Pageable pageable);
    Application updateApplication(Long id, Application application);
    void deleteApplication(Long id);
    Page<Application> getApplicationsByJobListing(Long jobListingId, Pageable pageable);
    Page<Application> getApplicationsByApplicant(Long applicantId, Pageable pageable);
    Page<Application> getApplicationsByStatus(String status, Pageable pageable);
    long countApplicationsByStatus(String status);
}
