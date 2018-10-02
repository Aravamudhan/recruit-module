package com.amudhan.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
// Enable fail method when adding new service methods and their corresponding tests. Disable it
// after the service method is implemented
// import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.amudhan.recruit.domain.JobApplication;
import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.amudhan.recruit.exception.EntityNotFoundException;
import com.amudhan.recruit.exception.JobApplicationException;

/**
 * Integration tests for the ApplicationService
 * 
 * @author amudhan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class JobApplicationServiceIntegrationTest {

  @Autowired
  private OfferService offerService;

  @Autowired
  private JobApplicationService jobApplicationService;

  private static final Logger log =
      LoggerFactory.getLogger(JobApplicationServiceIntegrationTest.class);

  private static Long OFFER_ID = null;

  private static Long JOB_APPLICATION_ID = null;

  /**
   * This test method is a setup method. Before applying for an offer we have to make sure that
   * offer exists. We are creating couple of offers here
   */
  @Test
  @Rollback(false)
  public void testA_Setup() {
    Offer offer =
        new Offer().jobTitle("Software Engineer I").startDate(LocalDateTime.now().plusDays(40));
    offer = offerService.create(offer);
    assertNotNull(offer);
    OFFER_ID = offer.getId();
    log.info("Offer is created in the test setup.....");
    log.info(offer.toString());
  }

  // Create a job application and apply for an offer
  @Test
  @Rollback(false)
  public void testB_ApplyService() throws EntityNotFoundException, JobApplicationException {
    Offer offer = offerService.findById(OFFER_ID);
    JobApplication jobApplication = new JobApplication("candidate1@mail.com", offer);
    jobApplication = jobApplicationService.apply(jobApplication);
    assertNotNull(jobApplication);
    assertNotNull(jobApplication.getId());
    assertNotNull(jobApplication.getOffer());
    assertNotNull(jobApplication.getOffer().getId());
    assertEquals("Offer ID", OFFER_ID, jobApplication.getOffer().getId());
    assertEquals("Offer", offer, jobApplication.getOffer());
    JOB_APPLICATION_ID = jobApplication.getId();
    log.info("JobApplication is created and is applied to an offer......");
    log.info(jobApplication.toString());
  }

  /**
   * Find a job application based on the id
   * 
   * @throws EntityNotFoundException
   */
  @Test
  public void testC_FindByIdService() throws EntityNotFoundException {
    JobApplication jobApplication = jobApplicationService.find(JOB_APPLICATION_ID);
    assertNotNull(jobApplication);
    assertNotNull(jobApplication.getId());
    assertNotNull(jobApplication.getOffer());
    assertNotNull(jobApplication.getOffer().getId());
    assertEquals("Offer ID", OFFER_ID, jobApplication.getOffer().getId());
    assertEquals("Job application id", JOB_APPLICATION_ID, jobApplication.getId());
  }

  /**
   * Find a job application by its id
   */
  @Test
  public void testC_FindByOfferIdService() {
    List<JobApplication> jobApplications = jobApplicationService.findByOfferId(OFFER_ID);
    assertNotNull(jobApplications);
    assertTrue(jobApplications.size() > 0);
    assertEquals("Offer id with job application's offer id", OFFER_ID,
        jobApplications.get(0).getOffer().getId());
    log.info("Job application by offer id:" + jobApplications.get(0).toString());
  }

  /**
   * Find a job application by offer id
   */
  @Test
  public void testC_FindAllService() {
    List<JobApplication> jobApplications = jobApplicationService.findAll();
    assertNotNull(jobApplications);
    assertTrue(jobApplications.size() > 0);
  }

  /**
   * Modifying application status
   * 
   * @throws EntityNotFoundException
   */
  @Test
  public void testD_ModifyStatusService() throws EntityNotFoundException {
    JobApplicationStatus status = JobApplicationStatus.INVITED;
    JobApplication jobApplication = jobApplicationService.updateStatus(JOB_APPLICATION_ID, status);
    assertNotNull(jobApplication);
    assertNotNull(jobApplication);
    assertNotNull(jobApplication.getId());
    assertNotNull(jobApplication.getOffer());
    assertNotNull(jobApplication.getOffer().getId());
    assertEquals("Offer ID", OFFER_ID, jobApplication.getOffer().getId());
    assertEquals("Job application status", status, jobApplication.getJobApplicationStatus());
  }

}
