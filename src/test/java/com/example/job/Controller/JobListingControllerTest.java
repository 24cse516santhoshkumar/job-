package com.example.job.Controller;

import com.example.job.Entity.JobListing;
import com.example.job.Service.JobListingService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobListingController.class)
public class JobListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private JobListingService jobListingService;

    @BeforeEach
    public void setUp() {
        jobListingService = Mockito.mock(JobListingService.class);
        // Removed redundant mockMvc initialization
    }

    @Test
    public void testCreateJobListing() throws Exception {
        JobListing jobListing = new JobListing();
        jobListing.setTitle("Software Engineer");
        jobListing.setDescription("Job description");
        jobListing.setLocation("New York");
        jobListing.setSalary(100000);
        jobListing.setJobType("Full-time");

        when(jobListingService.createJobListing(any(JobListing.class))).thenReturn(jobListing);

        mockMvc.perform(post("/api/job-listings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Software Engineer\",\"description\":\"Job description\",\"location\":\"New York\",\"salary\":100000,\"jobType\":\"Full-time\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Software Engineer"));
    }

    @Test
    public void testGetJobListingById() throws Exception {
        JobListing jobListing = new JobListing();
        jobListing.setId(1L);
        jobListing.setTitle("Software Engineer");
        
        when(jobListingService.getJobListingById(1L)).thenReturn(jobListing);

        mockMvc.perform(get("/api/job-listings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Software Engineer"));
    }

    @Test
    public void testUpdateJobListing() throws Exception {
        JobListing jobListing = new JobListing();
        jobListing.setId(1L);
        jobListing.setTitle("Updated Title");

        when(jobListingService.updateJobListing(any(Long.class), any(JobListing.class))).thenReturn(jobListing);

        mockMvc.perform(put("/api/job-listings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Updated Title\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    public void testDeleteJobListing() throws Exception {
        mockMvc.perform(delete("/api/job-listings/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"message\": \"Job listing deleted successfully.\"}"));
    }
}
