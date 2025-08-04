package com.example.magalu_desafio_backend.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "scheduling")
@Table(name = "tb_scheduling")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailAddressee;

    private String message;

    private String phoneAddressee;

    private LocalDateTime localDateTime;

    private NotificationStatusType notificationStatusType;
}
