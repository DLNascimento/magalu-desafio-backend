package com.example.magalu_desafio_backend.business.map;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.infra.entity.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    Notification paraNotificationEntity(NotificationDTO notificationDTO);
    NotificationOutDTO paraNotificationOutDTO(Notification notification);

}
