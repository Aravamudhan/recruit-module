package com.amudhan.recruit.service;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;

public interface JobApplicationService {
  public JobApplication apply(JobApplication jobApplication)
      throws EntityNotFoundException, JobApplicationException;
}
