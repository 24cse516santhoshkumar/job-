package com.example.job.Service;

import com.example.job.Entity.Application;
import com.example.job.Exception.ResourceNotFoundException;
import com.example.job.Repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    
    @Override
    public Page<Application> searchByApplicantName(String firstName, String lastName, Pageable pageable) {
        return applicationRepository.searchByApplicantName(firstName, lastName, pageable);
    }

    private final ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Page<Application> getAllApplications(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }


    @Override
    public Application updateApplication(Long id, Application application) {
        Application existingApplication = getApplicationById(id);
        existingApplication.setStatus(application.getStatus());
        return applicationRepository.save(existingApplication);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public Page<Application> getApplicationsByJobListing(Long jobListingId, Pageable pageable) {
        return applicationRepository.findByJobListingIdOrderByDate(jobListingId, pageable);
    }

    @Override
    public Page<Application> getApplicationsByApplicant(Long applicantId, Pageable pageable) {
        return applicationRepository.findByApplicantIdOrderByDate(applicantId, pageable);
    }

    @Override
    public Page<Application> getApplicationsByStatus(String status, Pageable pageable) {
        return applicationRepository.findByStatus(status, pageable);
    }

    @Override
    public long countApplicationsByStatus(String status) {
        return applicationRepository.countByStatus(status);
    }
}
