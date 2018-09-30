package com.amudhan.recruit.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * All the entities must extend from this super class
 * 
 * @author amudhan
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "date_created", nullable = false)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private ZonedDateTime dateCreated = ZonedDateTime.now();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(ZonedDateTime dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    AbstractEntity other = (AbstractEntity) obj;
    if (dateCreated == null) {
      if (other.dateCreated != null)
        return false;
    } else if (!dateCreated.equals(other.dateCreated))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "AbstractEntity [id=" + id + ", dateCreated=" + dateCreated + "]";
  }

}
