package com.example.magalu_desafio_backend.business.dto;

import com.example.magalu_desafio_backend.infra.entity.StatusType;

import java.time.LocalDateTime;

public record NotificationOutDTO(Long id, String emailAddressee, String phoneAddressee,
                                 String message, LocalDateTime schedulingDate,
                                 StatusType statusType) {
}
