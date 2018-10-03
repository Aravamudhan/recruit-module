package com.amudhan.recruit.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amudhan.recruit.domain.Notification;
import com.amudhan.recruit.repository.NotificationRepository;
import com.amudhan.recruit.service.NotificationService;

/**
 * Implementation of the NotificationService
 * 
 * @author amudhan
 *
 */
@Service
public class NotificationServiceImplementation implements NotificationService {

  private static final Logger log =
      LoggerFactory.getLogger(NotificationServiceImplementation.class);

  private final NotificationRepository notificationRepository;

  public NotificationServiceImplementation(final NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  /**
   * This method is called asynchronously as a notification service should
   */
  @Override
  @Async
  public void notify(Notification notification) {
    if (notification != null && notification.getEmail() != null
        && notification.getMessage() != null) {
      log.info("Notification is triggered for the email " + notification.getEmail());
      log.info("...................................................................");
      notification.setSent(true);
      notification = notificationRepository.save(notification);
      if (notification != null && notification.getId() != null) {
        log.info("Notification is send and it updated in the data store with the id "
            + notification.getId() + " for the email " + notification.getEmail());
      } else {
        log.error("Notification failed ");
      }
    } else {
      log.error("Invalid notification is triggered!!!!![" + notification + "]");
    }
  }

}
