package com.example.magalu_desafio_backend.controller;

import com.example.magalu_desafio_backend.business.dto.NotificationDTO;
import com.example.magalu_desafio_backend.business.dto.NotificationOutDTO;
import com.example.magalu_desafio_backend.business.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
@Tag(name = "Schedulling Notification")
public class NotificationController {

    private final NotificationService notificationService;


    @PostMapping
    @Operation(summary = "Cria Agendamentos", description = "Cria um novo agendamento com horário para ser notificado")
    @ApiResponse(responseCode = "200", description = "Agendamento salvo com sucesso!")
    @ApiResponse(responseCode = "500", description = "Erro de servidor!")
    public ResponseEntity<NotificationOutDTO> newCommunicationSchedule(@RequestBody NotificationDTO notificationDTO) {

        return ResponseEntity.ok(notificationService.newCommunicationSchedule(notificationDTO));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca agendamentos pelo Id", description = "Busca um agendamento especifico pelo id")
    @ApiResponse(responseCode = "200", description = "Id buscado com sucesso")
    @ApiResponse(responseCode = "403", description = "Id do agendamento não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<NotificationOutDTO> getCommunicationScheduleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notificationService.getCommunicationSchedules(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancela o agendamento", description = "Altera o Status do agendamento para Cancelado")
    @ApiResponse(responseCode = "200", description = "Agendamento cancelado")
    @ApiResponse(responseCode = "400", description = "Status não permitido para cancelamento")
    @ApiResponse(responseCode = "403", description = "Id do agendamento não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> cancelCommunicationSchedule(@PathVariable("id") Long id) {
        notificationService.cancelScheduling(id);
        return ResponseEntity.ok().build();
    }

}
