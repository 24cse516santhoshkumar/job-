package com.example.job.Repository;

import com.example.job.Entity.Employer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    Page<Employer> findAll(Pageable pageable);
    
    Page<Employer> findByCompanyNameContainingIgnoreCase(String companyName, Pageable pageable);
    
    @Query("SELECT e FROM Employer e WHERE e.email = :email")
    Page<Employer> findByEmail(@Param("email") String email, Pageable pageable);
    
    @Query("SELECT e FROM Employer e WHERE e.website IS NOT NULL ORDER BY e.companyName ASC")
    Page<Employer> findEmployersWithWebsite(Pageable pageable);
    
    @Query("SELECT e FROM Employer e WHERE e.address LIKE %:location%")
    Page<Employer> findByLocation(@Param("location") String location, Pageable pageable);
}
