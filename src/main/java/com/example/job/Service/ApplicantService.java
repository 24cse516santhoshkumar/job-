package com.example.job.Service;

import com.example.job.Entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ApplicantService {
    Applicant createApplicant(Applicant applicant);
    Applicant getApplicantById(Long id);
    List<Applicant> getAllApplicants();
    Page<Applicant> getAllApplicants(Pageable pageable);
    Page<Applicant> searchByFirstName(String firstName, Pageable pageable);
    Page<Applicant> searchByLastName(String lastName, Pageable pageable);
    Page<Applicant> searchByEmail(String email, Pageable pageable);
    Page<Applicant> getApplicantsWithResume(Pageable pageable);
    Applicant updateApplicant(Long id, Applicant applicant);
    void deleteApplicant(Long id);
}
