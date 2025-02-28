package com.example.job.Service;

import com.example.job.Entity.Applicant;
import com.example.job.Repository.ApplicantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Applicant createApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Applicant getApplicantById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    @Override
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    @Override
    public Page<Applicant> getAllApplicants(Pageable pageable) {
        return applicantRepository.findAll(pageable);
    }

    @Override
    public Page<Applicant> searchByFirstName(String firstName, Pageable pageable) {
        return applicantRepository.findByFirstName(firstName, pageable);
    }

    @Override
    public Page<Applicant> searchByLastName(String lastName, Pageable pageable) {
        return applicantRepository.findByLastName(lastName, pageable);
    }

    @Override
    public Page<Applicant> searchByEmail(String email, Pageable pageable) {
        return applicantRepository.findByEmail(email, pageable);
    }

    @Override
    public Page<Applicant> getApplicantsWithResume(Pageable pageable) {
        return applicantRepository.findByResumeUrlIsNotNull(pageable);
    }

    @Override
    public Applicant updateApplicant(Long id, Applicant applicant) {
        Applicant existingApplicant = getApplicantById(id);
        existingApplicant.setFirstName(applicant.getFirstName());
        existingApplicant.setLastName(applicant.getLastName());
        existingApplicant.setEmail(applicant.getEmail());
        existingApplicant.setPhone(applicant.getPhone());
        existingApplicant.setResumeUrl(applicant.getResumeUrl());
        return applicantRepository.save(existingApplicant);
    }

    @Override
    public void deleteApplicant(Long id) {
        applicantRepository.deleteById(id);
    }
}
