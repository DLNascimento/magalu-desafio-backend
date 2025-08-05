package com.example.magalu_desafio_backend.business.dto;

import java.time.LocalDateTime;

public record NotificationDTO(String emailAddressee, String phoneAddressee,
                              String message, LocalDateTime schedulingDate) {
}
