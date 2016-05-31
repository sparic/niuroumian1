package com.myee.domain.cleverm.dao;

import com.myee.domain.cleverm.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationDao extends JpaRepository<Notification, Long> {

}
