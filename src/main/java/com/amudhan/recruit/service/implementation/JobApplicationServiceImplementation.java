package com.amudhan.recruit.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.domain.Notification;
import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;
import com.amudhan.recruit.repository.JobApplicationRepository;
import com.amudhan.recruit.service.JobApplicationService;
import com.amudhan.recruit.service.NotificationService;
import com.amudhan.recruit.service.OfferService;

/**
 * Implementation of the JobApplication service
 * 
 * @author amudhan
 *
 */
@Service
public class JobApplicationServiceImplementation implements JobApplicationService {

  private final JobApplicationRepository jobApplicationRepository;

  private final OfferService offerService;

  private final NotificationService notificationService;

  public JobApplicationServiceImplementation(
      final JobApplicationRepository jobApplicationRepository, final OfferService offerService,
      final NotificationService notificationService) {
    this.jobApplicationRepository = jobApplicationRepository;
    this.offerService = offerService;
    this.notificationService = notificationService;
  }

  private JobApplication findJobApplicationChecked(Long id) throws EntityNotFoundException {
    return jobApplicationRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
  }

  @Override
  public JobApplication apply(JobApplication jobApplication)
      throws EntityNotFoundException, JobApplicationException {
    if (jobApplication == null) {
      throw new JobApplicationException("JobApplication can not null");
    }
    if (jobApplication.getEmail() == null || jobApplication.getEmail().isEmpty()) {
      throw new JobApplicationException("Email of the candidate is required");
    }
    if (jobApplication.getOffer() == null || jobApplication.getOffer().getId() == null) {
      throw new JobApplicationException("Offer ID of the job is required");
    }
    Offer offer = offerService.findById(jobApplication.getOffer().getId());
    offer.setJobApplicationsCount(offer.getJobApplicationsCount() + 1);
    jobApplication.setOffer(offer);
    jobApplication = jobApplicationRepository.save(jobApplication);
    // Notify an external service if the job application is saved successfully
    if (jobApplication.getId() != null && jobApplication.getId() > 0) {
      notificationService.notify(new Notification(jobApplication.getEmail(),
          "The current status of your application: "
              + jobApplication.getJobApplicationStatus().toString() + " for the job application id "
              + jobApplication.getId()));
    }
    return jobApplication;
  }

  @Override
  public JobApplication find(Long jobApplicationId) throws EntityNotFoundException {
    return findJobApplicationChecked(jobApplicationId);
  }

  @Override
  public List<JobApplication> findByOfferId(Long offerId) {
    return jobApplicationRepository.findByOfferId(offerId);
  }

  @Override
  public List<JobApplication> findAll() {
    return jobApplicationRepository.findAll();
  }

  @Override
  public JobApplication updateStatus(Long id, JobApplicationStatus status)
      throws EntityNotFoundException {
    JobApplication jobApplication = findJobApplicationChecked(id);
    jobApplication.setJobApplicationStatus(status);
    jobApplication = jobApplicationRepository.save(jobApplication);
    if (jobApplication.getId() != null && jobApplication.getId() > 0) {
      notificationService.notify(new Notification(jobApplication.getEmail(),
          "The current status of your application: "
              + jobApplication.getJobApplicationStatus().toString() + " for the job application id "
              + jobApplication.getId()));
    }
    return jobApplication;
  }

}
