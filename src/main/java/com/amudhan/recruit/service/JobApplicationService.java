package com.amudhan.recruit.service;

import java.util.List;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;

/**
 * Interface for the JobApplication service<br/>
 * The method updateStatus, find, findeByOfferId and findAll should be secured and should be
 * accessible only the admin user<br/>
 * 
 * @author amudhan
 *
 */
public interface JobApplicationService {

  public JobApplication apply(JobApplication jobApplication)
      throws EntityNotFoundException, JobApplicationException;

  public JobApplication find(Long jOB_APPLICATION_ID) throws EntityNotFoundException;

  public List<JobApplication> findByOfferId(Long offerId);

  public List<JobApplication> findAll();

  public JobApplication updateStatus(Long id, JobApplicationStatus status)
      throws EntityNotFoundException;
}
