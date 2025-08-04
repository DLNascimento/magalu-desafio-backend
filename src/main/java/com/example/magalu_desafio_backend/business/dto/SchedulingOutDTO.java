package com.example.magalu_desafio_backend.business.dto;

import com.example.magalu_desafio_backend.infra.entity.NotificationStatusType;

import java.time.LocalDateTime;

public record SchedulingOutDTO(Long id, String emailAddressee, String phoneAddressee,
                               String message, LocalDateTime schedulingLocalDateTime,
                               NotificationStatusType notificationStatusType) {
}
