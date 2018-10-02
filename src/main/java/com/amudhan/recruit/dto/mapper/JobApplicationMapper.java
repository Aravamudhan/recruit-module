package com.amudhan.recruit.dto.mapper;

import java.util.HashSet;
import java.util.Set;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.dto.JobApplicationDTO;

public class JobApplicationMapper {

  public static JobApplicationDTO toDto(JobApplication jobApplication) {
    JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
    return jobApplicationDTO;
  }

  public static Set<JobApplicationDTO> toDtoSet(Set<JobApplication> jobApplications) {
    Set<JobApplicationDTO> jobApplicationDtos = new HashSet<>();
    jobApplications.stream().forEach(entity -> jobApplicationDtos.add(toDto(entity)));
    return jobApplicationDtos;
  }

  public static JobApplication toEntity(JobApplicationDTO jobApplicationDTO) {
    JobApplication jobApplication = null;
    if (jobApplicationDTO != null) {
      jobApplication = new JobApplication(jobApplicationDTO.getEmail(),
          OfferMapper.toEntity(jobApplicationDTO.getOfferDTO()));
    }
    return jobApplication;
  }

}
