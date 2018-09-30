package com.amudhan.recruit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

import com.amudhan.recruit.domain.Offer;
import com.amudhan.recruit.exception.EntityNotFoundException;

/**
 * 1. This is the integration test for Offer service <br/>
 * 2. The FixMethodOrder annotation makes sure that the methods run in the ascending order. This is
 * needed so that we can test the entire flow of operations by starting with create and ending with
 * delete<br/>
 * 
 * @author amudhan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class OfferServiceIntegrationTest {

  @Autowired
  private OfferService offerService;

  private static final Logger log = LoggerFactory.getLogger(OfferServiceIntegrationTest.class);

  // After the create test completes, this will be set. All the operations depend on this
  // variable being that
  private static Long OFFER_ID = null;

  private static final String JOB_TITLE = "Senior Software Engineer I";

  private static final String JOB_TITLE_UPDATED = "Senior Software Engineer II";

  private static final LocalDateTime START_DATE = LocalDateTime.now().plusDays(20);

  private static final LocalDateTime START_DATE_UPDATED = LocalDateTime.now().plusDays(40);

  @Test
  @Rollback(false)
  public void testA_CreateServiceTest() {
    Offer offer = new Offer().jobTitle(JOB_TITLE).startDate(START_DATE);
    offer = offerService.create(offer);
    assertNotNull(offer);
    assertNotNull(offer.getId());
    assertTrue(offer.getId() > 0);
    log.info("Offer is created.....");
    log.info(offer.toString());
    OFFER_ID = offer.getId();
    assertEquals("Job title", JOB_TITLE, offer.getJobTitle());
    assertEquals("Start date", START_DATE, offer.getStartDate());
  }

  @Test
  public void testB_FindServiceTest() throws EntityNotFoundException {
    Offer offer = offerService.findById(OFFER_ID);
    assertNotNull(offer);
    assertNotNull(offer.getId());
    assertEquals("Offer ID", OFFER_ID, offer.getId());
    assertEquals("Job title", JOB_TITLE, offer.getJobTitle());
    assertEquals("Start date", START_DATE, offer.getStartDate());
    log.info("Offer is found.....");
    log.info(offer.toString());
  }

  @Test(expected = EntityNotFoundException.class)
  public void testB_FindFailServiceTest() throws EntityNotFoundException {
    Offer offer = offerService.findById(Long.MIN_VALUE);
    assertNull(offer);
  }

  @Test
  public void testB_FindAllServiceTest() throws EntityNotFoundException {
    List<Offer> offers = offerService.findAll();
    assertNotNull(offers);
    assertTrue(offers.size() > 1);
  }

  @Test
  public void testC_UpdateServiceTest() throws EntityNotFoundException {
    Offer offer = offerService.findById(OFFER_ID);
    log.info("Initial Offer.....");
    log.info(offer.toString());
    offer.setJobTitle(JOB_TITLE_UPDATED);
    offer.setStartDate(START_DATE_UPDATED);
    Offer updatedOffer = offerService.update(offer);
    assertNotNull("updatedOffer object", updatedOffer);
    assertEquals("Offer ID", OFFER_ID, updatedOffer.getId());
    assertEquals("Job title", JOB_TITLE_UPDATED, updatedOffer.getJobTitle());
    assertEquals("Start date", START_DATE_UPDATED, updatedOffer.getStartDate());
    log.info("Updated Offer.....");
    log.info(updatedOffer.toString());
  }

  // Create few job applications
  // Make them apply to the Offer
  // View them all

  @Test
  @Rollback(false)
  public void testD_deleteServiceTest() {
    offerService.deleteById(OFFER_ID);
  }

}
