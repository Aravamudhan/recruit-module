package com.amudhan.recruit.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.amudhan.recruit.dto.JobApplicationDTO;
import com.amudhan.recruit.dto.mapper.JobApplicationMapper;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;
import com.amudhan.recruit.service.JobApplicationService;

/**
 * Endpoints for JobApplications<br/>
 * TODO: Secure the endpoints<br/>
 * TODO: Except the apply endpoint all others should be secured and a candidate must not access any
 * other job application than themselves<br/>
 * TODO: Status modify endpoint must only be accessible for only admin users<br/>
 * 
 * @author amudhan
 *
 */
@RestController
@RequestMapping("v1/jobapplications")
public class JobApplicationController {

  private final JobApplicationService jobApplicationService;

  public JobApplicationController(final JobApplicationService jobApplicationService) {
    this.jobApplicationService = jobApplicationService;
  }

  @PostMapping("/apply")
  @ResponseStatus(HttpStatus.CREATED)
  public JobApplicationDTO apply(@RequestBody JobApplicationDTO jobApplicationDTO)
      throws EntityNotFoundException, JobApplicationException {
    JobApplication jobApplication = JobApplicationMapper.toEntity(jobApplicationDTO);
    jobApplication = jobApplicationService.apply(jobApplication);
    return JobApplicationMapper.toDto(jobApplication);
  }

  @GetMapping("/{jobApplicationId}")
  public JobApplicationDTO getJobApplicationById(@PathVariable long jobApplicationId)
      throws EntityNotFoundException {
    JobApplication jobApplication = jobApplicationService.find(jobApplicationId);
    JobApplicationDTO jobApplicationDTO = JobApplicationMapper.toDto(jobApplication);
    return jobApplicationDTO;
  }

  @GetMapping
  public List<JobApplicationDTO> getAllJobApplications() {
    return JobApplicationMapper.toDtoList(jobApplicationService.findAll());
  }

  @GetMapping("/offer/{offerId}")
  public List<JobApplicationDTO> getllAllJobApplicationsByOfferId(@PathVariable long offerId) {
    List<JobApplication> jobApplications = jobApplicationService.findByOfferId(offerId);
    return JobApplicationMapper.toDtoList(jobApplications);
  }

  @PutMapping("/{jobApplicationId}/status/{jobApplicationStatus}")
  public JobApplicationDTO updateStatus(@PathVariable Long jobApplicationId,
      @PathVariable JobApplicationStatus jobApplicationStatus) throws EntityNotFoundException {
    JobApplication jobApplication =
        jobApplicationService.updateStatus(jobApplicationId, jobApplicationStatus);
    return JobApplicationMapper.toDto(jobApplication);
  }

}
