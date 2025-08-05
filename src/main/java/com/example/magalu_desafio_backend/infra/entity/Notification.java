package com.example.magalu_desafio_backend.infra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emailAddressee;

    private String phoneAddressee;

    private String message;

    private LocalDateTime creationDate;

    private LocalDateTime schedulingDate;

    @Enumerated(EnumType.STRING)
    private StatusType statusType;
}
