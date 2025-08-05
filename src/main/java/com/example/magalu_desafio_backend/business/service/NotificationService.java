package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.business.map.NotificationMapper;
import com.example.magalu_desafio_backend.infra.entity.StatusType;
import com.example.magalu_desafio_backend.infra.entity.Notification;
import com.example.magalu_desafio_backend.infra.exceptions.NotFoundException;
import com.example.magalu_desafio_backend.infra.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;


    public NotificationOutDTO newCommunicationSchedule(NotificationDTO notificationDTO) {

        Notification notificationEntity = notificationMapper.paraNotificationEntity(notificationDTO);
        notificationEntity.setCreationDate(LocalDateTime.now());
        notificationEntity.setStatusType(StatusType.SCHEDULED);
        notificationRepository.save(notificationEntity);
        return notificationMapper.paraNotificationOutDTO(notificationEntity);

    }

    public NotificationOutDTO getCommunicationSchedules(Long id) {

        return notificationMapper.paraNotificationOutDTO(notificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Id não encontrado")));

    }

    public void cancelScheduling(Long id) {

        Notification notificationEntity = notificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("id não encontrado"));

        notificationEntity.setStatusType(StatusType.CANCELED);

        notificationMapper.paraNotificationOutDTO(notificationRepository.save(notificationEntity));


    }


}
