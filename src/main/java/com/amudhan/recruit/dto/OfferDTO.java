package com.amudhan.recruit.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDTO {

  private Long id;

  private String jobTitle;

  private LocalDateTime startDate;

  private Integer jobApplicationsCount;

  private Set<JobApplicationDTO> jobApplications = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OfferDTO id(Long id) {
    this.id = id;
    return this;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public OfferDTO jobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public OfferDTO startDate(LocalDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  public Integer getJobApplicationsCount() {
    return jobApplicationsCount;
  }

  public void setJobApplicationsCount(Integer jobApplicationsCount) {
    this.jobApplicationsCount = jobApplicationsCount;
  }

  public OfferDTO jobApplicationsCount(Integer jobApplicationsCount) {
    this.jobApplicationsCount = jobApplicationsCount;
    return this;
  }

  public Set<JobApplicationDTO> getJobApplications() {
    return jobApplications;
  }

  public void setJobApplications(Set<JobApplicationDTO> jobApplications) {
    this.jobApplications = jobApplications;
  }

  public OfferDTO jobApplications(Set<JobApplicationDTO> jobApplications) {
    this.jobApplications = jobApplications;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((jobApplications == null) ? 0 : jobApplications.hashCode());
    result =
        prime * result + ((jobApplicationsCount == null) ? 0 : jobApplicationsCount.hashCode());
    result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
    OfferDTO other = (OfferDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (jobApplications == null) {
      if (other.jobApplications != null)
        return false;
    } else if (!jobApplications.equals(other.jobApplications))
      return false;
    if (jobApplicationsCount == null) {
      if (other.jobApplicationsCount != null)
        return false;
    } else if (!jobApplicationsCount.equals(other.jobApplicationsCount))
      return false;
    if (jobTitle == null) {
      if (other.jobTitle != null)
        return false;
    } else if (!jobTitle.equals(other.jobTitle))
      return false;
    if (startDate == null) {
      if (other.startDate != null)
        return false;
    } else if (!startDate.equals(other.startDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "OfferDTO [id=" + id + ", jobTitle=" + jobTitle + ", startDate=" + startDate
        + ", jobApplicationsCount=" + jobApplicationsCount + "]";
  }


}
