package com.example.magalu_desafio_backend.controller;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.business.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    public ResponseEntity<NotificationOutDTO> newCommunicationSchedule(@RequestBody NotificationDTO notificationDTO) {

        return ResponseEntity.ok(notificationService.newCommunicationSchedule(notificationDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationOutDTO> getCommunicationScheduleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.getCommunicationSchedules(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelCommunicationSchedule(@PathVariable("id") Long id) {
        notificationService.cancelScheduling(id);
        return ResponseEntity.ok().build();
    }

}
