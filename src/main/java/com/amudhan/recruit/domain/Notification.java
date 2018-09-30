package com.amudhan.recruit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification extends AbstractEntity {
  @Column(name = "email")
  private String email;

  @Column(name = "message")
  private String message;

  @Column(name = "sent")
  private boolean sent = false;

  public Notification() {

  }

  public Notification(String email, String message) {
    this.email = email;
    this.message = message;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isSent() {
    return sent;
  }

  public void setSent(boolean sent) {
    this.sent = sent;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((message == null) ? 0 : message.hashCode());
    result = prime * result + (sent ? 1231 : 1237);
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
    Notification other = (Notification) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (message == null) {
      if (other.message != null)
        return false;
    } else if (!message.equals(other.message))
      return false;
    if (sent != other.sent)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Notification [email=" + email + ", message=" + message + ", sent=" + sent + "]";
  }


}
