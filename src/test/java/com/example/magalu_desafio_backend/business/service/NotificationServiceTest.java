package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.business.map.NotificationMapper;
import com.example.magalu_desafio_backend.infra.entity.Notification;
import com.example.magalu_desafio_backend.infra.entity.StatusType;
import com.example.magalu_desafio_backend.infra.exceptions.NotFoundException;
import com.example.magalu_desafio_backend.infra.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {


    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @InjectMocks
    private NotificationService notificationService;

    private NotificationDTO notificationDTO;
    private NotificationOutDTO notificationOutDTO;
    private Notification notification;


    @BeforeEach
    void setup() {
        notificationDTO = new NotificationDTO(
                "emailteste@email.com",
                "12321314",
                "Olá mundo",
                LocalDateTime.now().plusHours(1));

        notification = new Notification(
                1L,
                "emailteste@email.com",
                "12321314",
                "Olá mundo!",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                StatusType.SCHEDULED);


        notificationOutDTO = new NotificationOutDTO(1L,
                "emailteste@email.com",
                "12321314",
                "Olá mundo!",
                LocalDateTime.now().plusHours(1),
                StatusType.SCHEDULED);

    }

    @Test
    void mustCreateNotificationWithScheduledStatus(){
        when(notificationMapper.paraNotificationEntity(notificationDTO)).thenReturn(notification);
        when(notificationMapper.paraNotificationOutDTO(notification)).thenReturn(notificationOutDTO);

        notificationOutDTO = notificationService.newCommunicationSchedule(notificationDTO);

        assertNotNull(notificationOutDTO);
        assertEquals(StatusType.SCHEDULED, notification.getStatusType());
        verify(notificationRepository).save(notification);

    }

    @Test
    void shouldReturnNotificationById(){
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationMapper.paraNotificationOutDTO(notification)).thenReturn(notificationOutDTO);

        notificationOutDTO = notificationService.getCommunicationSchedules(1L);

        assertNotNull(notificationOutDTO);
        verify(notificationRepository).findById(1L);
    }

    @Test
    void shouldReturnExceptionWhenNotFoundById(){
        when(notificationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> notificationService.getCommunicationSchedules(1L));
    }

    @Test
    void shouldCancelNotificationWhenIdExists(){
        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));
        when(notificationRepository.save(notification)).thenReturn(notification);
        when(notificationMapper.paraNotificationOutDTO(notification)).thenReturn(notificationOutDTO);

        notificationService.cancelScheduling(1L);

        assertNotNull(notification);
        assertEquals(StatusType.CANCELED, notification.getStatusType());
        verify(notificationRepository).save(notification);
    }

    @Test
    void shouldThrowBadRequestWhenStatusIsNotScheduled() {
        notification.setStatusType(StatusType.SENT);

        when(notificationRepository.findById(1L)).thenReturn(Optional.of(notification));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            notificationService.cancelScheduling(1L);
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Somente quando o status for SCHEDULED é possível fazer o cancelamento", exception.getReason());

        verify(notificationRepository).findById(1L);
        verifyNoMoreInteractions(notificationRepository);
    }



    @Test
    void shouldReturnExceptionNotFoundById(){
        when(notificationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> notificationService.cancelScheduling(1L));
    }

    @Test
    void MustUpdateStatusToSentWhenPastTime(){

        Notification later = new Notification(2L,
                "emailteste",
                "313134532",
                "Olá mundo!",
                LocalDateTime.now(),
                LocalDateTime.now().minusHours(1),
                StatusType.SCHEDULED);

        when(notificationRepository.findByStatusType(StatusType.SCHEDULED)).thenReturn(List.of(later));

        notificationService.checkStatus();

        assertEquals(StatusType.SENT, later.getStatusType());
        verify(notificationRepository).save(later);

    }


}
