package com.example.magalu_desafio_backend.infra.repository;

import com.example.magalu_desafio_backend.infra.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {




}


