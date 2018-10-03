package com.amudhan.recruit.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.dto.JobApplicationDTO;
import com.amudhan.recruit.dto.OfferDTO;

public class JobApplicationMapper {

  public static JobApplicationDTO toDto(JobApplication jobApplication) {
    JobApplicationDTO jobApplicationDTO = new JobApplicationDTO().email(jobApplication.getEmail())
        .id(jobApplication.getId()).jobApplicationStatus(jobApplication.getJobApplicationStatus());
    Offer offer = jobApplication.getOffer();
    OfferDTO offerDTO =
        new OfferDTO().jobTitle(offer.getJobTitle()).startDate(offer.getStartDate());
    jobApplicationDTO.setOfferDTO(offerDTO);
    return jobApplicationDTO;
  }

  public static List<JobApplicationDTO> toDtoList(List<JobApplication> jobApplications) {
    List<JobApplicationDTO> jobApplicationDtos = new ArrayList<>();
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
