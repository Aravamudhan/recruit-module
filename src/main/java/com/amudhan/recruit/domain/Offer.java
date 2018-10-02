package com.amudhan.recruit.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity that represents a row in the offer table
 * 
 * @author amudhan
 *
 */
@Entity
@Table(name = "offer",
    uniqueConstraints = @UniqueConstraint(name = "uc_job_title", columnNames = {"job_title"}))
public class Offer extends AbstractEntity {

  @Column(name = "job_title", unique = true)
  private String jobTitle;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "job_applications_count")
  private Integer jobApplicationsCount = 0;

  @OneToMany(mappedBy = "offer")
  private Set<JobApplication> jobApplications = new HashSet<>();

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public Integer getJobApplicationsCount() {
    return jobApplicationsCount;
  }

  public void setJobApplicationsCount(Integer jobApplicationsCount) {
    this.jobApplicationsCount = jobApplicationsCount;
  }

  public Set<JobApplication> getJobApplications() {
    return jobApplications;
  }

  public void setJobApplications(Set<JobApplication> jobApplications) {
    this.jobApplications = jobApplications;
  }

  // Creating builders for the properties
  public Offer jobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  public Offer startDate(LocalDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((jobApplications == null) ? 0 : jobApplications.hashCode());
    result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
    result =
        prime * result + ((jobApplicationsCount == null) ? 0 : jobApplicationsCount.hashCode());
    result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Offer other = (Offer) obj;
    if (jobApplications == null) {
      if (other.jobApplications != null)
        return false;
    } else if (!jobApplications.equals(other.jobApplications))
      return false;
    if (jobTitle == null) {
      if (other.jobTitle != null)
        return false;
    } else if (!jobTitle.equals(other.jobTitle))
      return false;
    if (jobApplicationsCount == null) {
      if (other.jobApplicationsCount != null)
        return false;
    } else if (!jobApplicationsCount.equals(other.jobApplicationsCount))
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
    return "Offer [jobTitle=" + jobTitle + ", startDate=" + startDate + ", jobApplicationsCount="
        + jobApplicationsCount + ", id=" + getId() + ", dateCreated=" + getDateCreated() + "]";
  }



}
