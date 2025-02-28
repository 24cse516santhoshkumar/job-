package com.example.job.Service;

import com.example.job.Entity.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployerService {
    Employer createEmployer(Employer employer);
    Employer getEmployerById(Long id);
    List<Employer> getAllEmployers();
    Page<Employer> getAllEmployers(Pageable pageable);
    Page<Employer> searchByCompanyName(String companyName, Pageable pageable);
    Page<Employer> searchByEmail(String email, Pageable pageable);
    Page<Employer> getEmployersWithWebsite(Pageable pageable);
    Page<Employer> searchByLocation(String location, Pageable pageable);
    Employer updateEmployer(Long id, Employer employer);
    void deleteEmployer(Long id);
}
