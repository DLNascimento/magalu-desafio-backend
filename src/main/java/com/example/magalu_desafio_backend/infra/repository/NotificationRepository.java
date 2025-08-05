package com.example.magalu_desafio_backend.infra.repository;

import com.example.magalu_desafio_backend.infra.entity.Notification;
import com.example.magalu_desafio_backend.infra.entity.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatusType(StatusType statusType);


}


