package com.amudhan.recruit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amudhan.recruit.RecruitModuleApplication;
import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.amudhan.recruit.dto.JobApplicationDTO;
import com.amudhan.recruit.dto.OfferDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {RecruitModuleApplication.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobApplicationIntegrationTest {

  private static Long OFFER_ID = null;

  private static Long JOB_APPLICATION_ID = null;

  private static final String CANDIDATE_EMAIL = "candidatetest1@mail.com";

  private static final String JOB_TITLE = "Senior Software Engineer I";

  private static final LocalDateTime START_DATE = LocalDateTime.now().plusDays(30);

  private static final JobApplicationStatus JOB_APPLICATION_STATUS_UPDATED =
      JobApplicationStatus.INVITED;

  private static final String JOB_APPLICATION_ENDPOINT = "/v1/jobapplications";

  private static final String OFFER_ENDPOINT = "/v1/offers";

  private static final Logger log = LoggerFactory.getLogger(JobApplicationIntegrationTest.class);

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ObjectMapper mapper;


  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void testA_setup() throws JsonProcessingException, Exception {
    OfferDTO offerDTO = new OfferDTO().jobTitle(JOB_TITLE).startDate(START_DATE);
    MvcResult result = mockMvc
        .perform(post(OFFER_ENDPOINT).header("Content-Type", "application/json")
            .content(mapper.writeValueAsString(offerDTO)))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(JOB_TITLE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").isNotEmpty()).andReturn();
    String offerResponseContent = result.getResponse().getContentAsString();
    log.info("Offer created response :" + offerResponseContent);
    JsonNode offerResponseJsonContent = mapper.readTree(offerResponseContent);
    OFFER_ID = offerResponseJsonContent.get("id").asLong();
  }

  @Test
  public void testB_applyTest() throws JsonProcessingException, Exception {
    OfferDTO offerDTO = new OfferDTO().id(OFFER_ID);
    JobApplicationDTO dto = new JobApplicationDTO().offerDTO(offerDTO).email(CANDIDATE_EMAIL);
    MvcResult result = mockMvc
        .perform(post(JOB_APPLICATION_ENDPOINT + "/apply")
            .header("Content-Type", "application/json").content(mapper.writeValueAsString(dto)))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(CANDIDATE_EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobApplicationStatus")
            .value(JobApplicationStatus.APPLIED.toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO").isNotEmpty())
        .andExpect(
            MockMvcResultMatchers.jsonPath("$.offerDTO.startDate").value(START_DATE.toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO.jobTitle").value(JOB_TITLE))
        .andReturn();
    String jobApplicationResponseContent = result.getResponse().getContentAsString();
    log.info("Job application created response :" + jobApplicationResponseContent);
    JsonNode jobApplicationResponseJsonContent = mapper.readTree(jobApplicationResponseContent);
    JOB_APPLICATION_ID = jobApplicationResponseJsonContent.get("id").asLong();
  }

  @Test
  public void testC_getTest() throws Exception {
    mockMvc
        .perform(get(JOB_APPLICATION_ENDPOINT + "/" + JOB_APPLICATION_ID).header("Content-Type",
            "application/json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(JOB_APPLICATION_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(CANDIDATE_EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO.startDate").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO.jobTitle").value(JOB_TITLE))
        .andReturn();
  }

  @Test
  public void testC_getAllTest() throws Exception {
    mockMvc.perform(get(JOB_APPLICATION_ENDPOINT).header("Content-Type", "application/json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(JOB_APPLICATION_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(CANDIDATE_EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO.startDate").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO.jobTitle").value(JOB_TITLE));
  }

  @Test
  public void testC_getAllByOfferIdTest() throws Exception {
    mockMvc
        .perform(get(JOB_APPLICATION_ENDPOINT + "/offer/" + OFFER_ID).header("Content-Type",
            "application/json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(JOB_APPLICATION_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(CANDIDATE_EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO.startDate").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].offerDTO.jobTitle").value(JOB_TITLE));
  }

  @Test
  public void testD_updateStatusTest() throws Exception {
    mockMvc
        .perform(put(JOB_APPLICATION_ENDPOINT + "/" + JOB_APPLICATION_ID + "/status/"
            + JOB_APPLICATION_STATUS_UPDATED).header("Content-Type", "application/json"))
        .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(CANDIDATE_EMAIL))
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobApplicationStatus")
            .value(JOB_APPLICATION_STATUS_UPDATED.toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO.startDate").isNotEmpty())
        .andExpect(MockMvcResultMatchers.jsonPath("$.offerDTO.jobTitle").value(JOB_TITLE));
  }

}
