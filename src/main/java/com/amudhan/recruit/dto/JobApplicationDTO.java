package com.amudhan.recruit.dto;

import com.amudhan.recruit.domain.value.JobApplicationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobApplicationDTO {

  private Long id;

  private String email;

  private OfferDTO offerDTO;

  private JobApplicationStatus jobApplicationStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public JobApplicationDTO id(Long id) {
    this.id = id;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public JobApplicationDTO email(String email) {
    this.email = email;
    return this;
  }

  public OfferDTO getOfferDTO() {
    return offerDTO;
  }

  public void setOfferDTO(OfferDTO offerDTO) {
    this.offerDTO = offerDTO;
  }

  public JobApplicationDTO offerDTO(OfferDTO offerDTO) {
    this.offerDTO = offerDTO;
    return this;

  }

  public JobApplicationStatus getJobApplicationStatus() {
    return jobApplicationStatus;
  }

  public void setJobApplicationStatus(JobApplicationStatus jobApplicationStatus) {
    this.jobApplicationStatus = jobApplicationStatus;
  }

  public JobApplicationDTO jobApplicationStatus(JobApplicationStatus jobApplicationStatus) {
    this.jobApplicationStatus = jobApplicationStatus;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result =
        prime * result + ((jobApplicationStatus == null) ? 0 : jobApplicationStatus.hashCode());
    result = prime * result + ((offerDTO == null) ? 0 : offerDTO.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    JobApplicationDTO other = (JobApplicationDTO) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (jobApplicationStatus != other.jobApplicationStatus)
      return false;
    if (offerDTO == null) {
      if (other.offerDTO != null)
        return false;
    } else if (!offerDTO.equals(other.offerDTO))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "JobApplicationDTO [id=" + id + ", email=" + email + ", jobApplicationStatus="
        + jobApplicationStatus + "]";
  }


}
