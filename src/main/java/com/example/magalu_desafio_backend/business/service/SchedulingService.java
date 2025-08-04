package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.SchedulingDTO;
import com.example.magalu_desafio_backend.business.map.SchedulingMapper;
import com.example.magalu_desafio_backend.infra.entity.NotificationStatusType;
import com.example.magalu_desafio_backend.infra.entity.Scheduling;
import com.example.magalu_desafio_backend.infra.repository.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final SchedulingMapper schedulingMapper;


    public SchedulingDTO schedulingCommunication(SchedulingDTO schedulingDTO, Long id) {

        Scheduling schedulingEntity = schedulingMapper.paraSchedulingEntity(schedulingDTO);
        schedulingEntity.setLocalDateTime(LocalDateTime.now());
        schedulingEntity.setNotificationStatusType(NotificationStatusType.PENDING);
        schedulingRepository.save(schedulingEntity);
        return schedulingMapper.paraSchedulingDTO(schedulingEntity);

    }

}
