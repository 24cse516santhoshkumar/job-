package com.example.job.Service;

import com.example.job.Entity.Employer;
import com.example.job.Exception.ResourceNotFoundException;
import com.example.job.Repository.EmployerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    public EmployerServiceImpl(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    @Override
    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    @Override
    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found with id: " + id));
    }

    @Override
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    @Override
    public Page<Employer> getAllEmployers(Pageable pageable) {
        return employerRepository.findAll(pageable);
    }

    @Override
    public Page<Employer> searchByCompanyName(String companyName, Pageable pageable) {
        return employerRepository.findByCompanyNameContainingIgnoreCase(companyName, pageable);
    }

    @Override
    public Page<Employer> searchByEmail(String email, Pageable pageable) {
        return employerRepository.findByEmail(email, pageable);
    }

    @Override
    public Page<Employer> getEmployersWithWebsite(Pageable pageable) {
        return employerRepository.findEmployersWithWebsite(pageable);
    }

    @Override
    public Page<Employer> searchByLocation(String location, Pageable pageable) {
        return employerRepository.findByLocation(location, pageable);
    }

    @Override
    public Employer updateEmployer(Long id, Employer employer) {
        Employer existingEmployer = getEmployerById(id);
        existingEmployer.setCompanyName(employer.getCompanyName());
        existingEmployer.setEmail(employer.getEmail());
        existingEmployer.setPhone(employer.getPhone());
        existingEmployer.setWebsite(employer.getWebsite());
        existingEmployer.setAddress(employer.getAddress());
        return employerRepository.save(existingEmployer);
    }

    @Override
    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}
