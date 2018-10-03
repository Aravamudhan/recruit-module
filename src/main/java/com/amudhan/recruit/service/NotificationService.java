package com.amudhan.recruit.service;

import com.amudhan.recruit.domain.Notification;

/**
 * Notification service interface<br/>
 * The notify method can be implemented to send the notification to any external notification
 * platforms that sends email/messages or provide the notification functionality in any way
 * 
 * @author amudhan
 *
 */
public interface NotificationService {
  public void notify(Notification notification);
}
