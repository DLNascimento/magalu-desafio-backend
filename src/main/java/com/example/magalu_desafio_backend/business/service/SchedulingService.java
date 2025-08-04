package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.SchedulingDTO;
import com.example.magalu_desafio_backend.business.dto.SchedulingOutDTO;
import com.example.magalu_desafio_backend.business.map.SchedulingMapper;
import com.example.magalu_desafio_backend.infra.entity.NotificationStatusType;
import com.example.magalu_desafio_backend.infra.entity.Scheduling;
import com.example.magalu_desafio_backend.infra.exceptions.NotFoundException;
import com.example.magalu_desafio_backend.infra.repository.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final SchedulingMapper schedulingMapper;


    public SchedulingOutDTO newCommunicationSchedule(SchedulingDTO schedulingDTO) {

        Scheduling schedulingEntity = schedulingMapper.paraSchedulingEntity(schedulingDTO);
        schedulingEntity.setLocalDateTime(LocalDateTime.now());
        schedulingEntity.setNotificationStatusType(NotificationStatusType.PENDING);
        schedulingRepository.save(schedulingEntity);
        return schedulingMapper.paraSchedulingOutDTO(schedulingEntity);

    }

    public SchedulingOutDTO getCommunicationSchedules(Long id) {

        return schedulingMapper.paraSchedulingOutDTO(schedulingRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Id não encontrado")));

    }

}
