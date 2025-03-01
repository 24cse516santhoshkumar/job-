package com.example.job.Controller;

import com.example.job.Entity.Applicant;
import com.example.job.Service.ApplicantService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {
    
    @GetMapping("/firstName")
    public Page<Applicant> findByFirstName(@RequestParam String firstName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return applicantService.searchByFirstName(firstName, PageRequest.of(page, size));
    }

    @GetMapping("/lastName")
    public Page<Applicant> findByLastName(@RequestParam String lastName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return applicantService.searchByLastName(lastName, PageRequest.of(page, size));
    }

    @GetMapping("/email")
    public Page<Applicant> findByEmail(@RequestParam String email, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return applicantService.searchByEmail(email, PageRequest.of(page, size));
    }

    private final ApplicantService applicantService;

    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @GetMapping
    public Page<Applicant> getAllApplicants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        return applicantService.getAllApplicants(PageRequest.of(page, size, Sort.by(sort)));
    }

    @GetMapping("/{id}")
    public Applicant getApplicantById(@PathVariable Long id) {
        return applicantService.getApplicantById(id);
    }

    @PostMapping
    public Applicant createApplicant(@RequestBody Applicant applicant) {
        return applicantService.createApplicant(applicant);
    }

    @PutMapping("/{id}")
    public Applicant updateApplicant(@PathVariable Long id, @RequestBody Applicant applicant) {
        return applicantService.updateApplicant(id, applicant);
    }

    @DeleteMapping("/{id}")
    public void deleteApplicant(@PathVariable Long id) {
        applicantService.deleteApplicant(id);
    }
}
