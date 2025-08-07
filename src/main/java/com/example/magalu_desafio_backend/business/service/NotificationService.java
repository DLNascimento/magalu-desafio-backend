package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.business.map.NotificationMapper;
import com.example.magalu_desafio_backend.infra.entity.StatusType;
import com.example.magalu_desafio_backend.infra.entity.Notification;
import com.example.magalu_desafio_backend.infra.exceptions.NotFoundException;
import com.example.magalu_desafio_backend.infra.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Scheduled(fixedRate = 60000)
    public void checkStatus() {
        log.info("Checando o status de cada agendamento");
        List<Notification> scheduled = notificationRepository.findByStatusType(StatusType.SCHEDULED);

        for (Notification notification : scheduled) {
            if (!notification.getSchedulingDate().isAfter(LocalDateTime.now())) {
                log.info("Checando se tem algum agendamento fora do horario agendado");
                notification.setStatusType(StatusType.SENT);
                log.info("Trocando o Status para enviado/sent");
                notificationRepository.save(notification);
            }
        }
    }


    public NotificationOutDTO getCommunicationSchedules(Long id) {

        return notificationMapper.paraNotificationOutDTO(notificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Id não encontrado")));

    }

    public void cancelScheduling(Long id) {

        Notification notificationEntity = notificationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("id não encontrado"));
        if (!notificationEntity.getStatusType().equals(StatusType.SCHEDULED)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Somente quando o status for SCHEDULED é possível fazer o cancelamento");
        }
        notificationEntity.setStatusType(StatusType.CANCELED);
        notificationMapper.paraNotificationOutDTO(notificationRepository.save(notificationEntity));


    }
}
