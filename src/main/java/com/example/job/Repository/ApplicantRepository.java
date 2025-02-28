package com.example.job.Repository;

import com.example.job.Entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Page<Applicant> findByResumeUrlIsNotNull(Pageable pageable);

    @Query("SELECT a FROM Applicant a WHERE a.firstName LIKE %:firstName%")
    Page<Applicant> findByFirstName(@Param("firstName") String firstName, Pageable pageable);

    @Query("SELECT a FROM Applicant a WHERE a.lastName LIKE %:lastName%")
    Page<Applicant> findByLastName(@Param("lastName") String lastName, Pageable pageable);

    @Query("SELECT a FROM Applicant a WHERE a.email LIKE %:email%")
    Page<Applicant> findByEmail(@Param("email") String email, Pageable pageable);
}
