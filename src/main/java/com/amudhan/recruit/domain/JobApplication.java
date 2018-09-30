package com.amudhan.recruit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.amudhan.recruit.domain.value.JobApplicationStatus;

@Entity
@Table(name = "jobapplication",
    uniqueConstraints = @UniqueConstraint(name = "uc_email", columnNames = {"email"}))
public class JobApplication extends AbstractEntity {

  @Column(name = "email")
  private String email;

  @ManyToOne
  private Offer offer;

  @Enumerated(EnumType.STRING)
  private JobApplicationStatus jobApplicationStatus = JobApplicationStatus.APPLIED;

  public JobApplication(String email, Offer offer) {
    this.email = email;
    this.offer = offer;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Offer getOffer() {
    return offer;
  }

  public void setOffer(Offer offer) {
    this.offer = offer;
  }

  public JobApplicationStatus getJobApplicationStatus() {
    return jobApplicationStatus;
  }

  public void setJobApplicationStatus(JobApplicationStatus jobApplicationStatus) {
    this.jobApplicationStatus = jobApplicationStatus;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((jobApplicationStatus == null) ? 0 : jobApplicationStatus.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((offer == null) ? 0 : offer.hashCode());
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
    JobApplication other = (JobApplication) obj;
    if (jobApplicationStatus != other.jobApplicationStatus)
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (offer == null) {
      if (other.offer != null)
        return false;
    } else if (!offer.equals(other.offer))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Application [email=" + email + ", offer=" + offer + ", jobApplicationStatus="
        + jobApplicationStatus + "]";
  }


}
