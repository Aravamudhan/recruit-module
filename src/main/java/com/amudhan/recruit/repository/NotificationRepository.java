package com.amudhan.recruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amudhan.recruit.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
