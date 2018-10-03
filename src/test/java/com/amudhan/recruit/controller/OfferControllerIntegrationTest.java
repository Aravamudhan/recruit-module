package com.amudhan.recruit.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.amudhan.recruit.dto.OfferDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {RecruitModuleApplication.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfferControllerIntegrationTest {

  private static Long OFFER_ID = null;

  private static final String JOB_TITLE = "Senior Software Engineer I";

  private static final String JOB_TITLE_UPDATED = "Senior Software Engineer II";

  private static final LocalDateTime START_DATE = LocalDateTime.now().plusDays(30);

  private static final LocalDateTime START_DATE_UPDATED = LocalDateTime.now().plusDays(50);

  private static final String endpoint = "/v1/offers";

  private static final Logger log = LoggerFactory.getLogger(OfferControllerIntegrationTest.class);

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;


  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void testA_CreateOfferTest() throws JsonProcessingException, Exception {
    OfferDTO offerDTO = new OfferDTO().jobTitle(JOB_TITLE).startDate(START_DATE);
    MvcResult result = mockMvc
        .perform(post(endpoint).header("Content-Type", "application/json")
            .content(mapper.writeValueAsString(offerDTO)))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(JOB_TITLE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(START_DATE.toString()))
        .andReturn();
    String offerResponseContent = result.getResponse().getContentAsString();
    log.info("Offer created response :" + offerResponseContent);
    JsonNode offerResponseJsonContent = mapper.readTree(offerResponseContent);
    OFFER_ID = offerResponseJsonContent.get("id").asLong();
  }

  @Test
  public void testB_FindOfferTest() throws Exception {
    mockMvc.perform(get(endpoint + "/" + OFFER_ID).header("Content-Type", "application/json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(OFFER_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(JOB_TITLE))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(START_DATE.toString()));
  }

  @Test
  public void testB_FindAllOffersTest() throws Exception {
    mockMvc.perform(get(endpoint).header("Content-Type", "application/json"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(OFFER_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].jobTitle").value(JOB_TITLE))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].startDate").value(START_DATE.toString()));
  }

  @Test
  public void testC_UpdateOfferTest() throws JsonProcessingException, Exception {
    OfferDTO offerDTO =
        new OfferDTO().id(OFFER_ID).jobTitle(JOB_TITLE_UPDATED).startDate(START_DATE_UPDATED);
    mockMvc
        .perform(put(endpoint).header("Content-Type", "application/json")
            .content(mapper.writeValueAsString(offerDTO)))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(OFFER_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$.jobTitle").value(JOB_TITLE_UPDATED)).andExpect(
            MockMvcResultMatchers.jsonPath(".startDate").value(START_DATE_UPDATED.toString()));

  }

  @Test
  public void testD_DeleteOfferTest() throws Exception {
    mockMvc.perform(delete(endpoint + "/" + OFFER_ID).header("Content-Type", "application/json"))
        .andExpect(status().isOk());
  }

}
