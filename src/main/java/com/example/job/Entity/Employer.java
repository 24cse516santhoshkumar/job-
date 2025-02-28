package com.example.job.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String companyName;
    private String email;
    private String phone;
    private String website;
    private String address;
    
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private Set<JobListing> jobListings;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Set<JobListing> getJobListings() { return jobListings; }
    public void setJobListings(Set<JobListing> jobListings) { this.jobListings = jobListings; }
}
