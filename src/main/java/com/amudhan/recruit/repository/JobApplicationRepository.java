package com.amudhan.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amudhan.recruit.domain.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

}
