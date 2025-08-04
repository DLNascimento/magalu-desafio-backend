package com.example.magalu_desafio_backend.business.service;

import com.example.magalu_desafio_backend.business.dto.SchedulingDTO;
import com.example.magalu_desafio_backend.business.map.SchedulingMapper;
import com.example.magalu_desafio_backend.infra.entity.Scheduling;
import com.example.magalu_desafio_backend.infra.repository.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final SchedulingMapper schedulingMapper;


    public SchedulingDTO schedulingCommunication(SchedulingDTO schedulingDTO){

        Scheduling schedulingEntity = schedulingMapper.paraSchedulingEntity(schedulingDTO);
        schedulingRepository.save(schedulingEntity);
        return schedulingMapper.paraSchedulingDTO(schedulingEntity);

    }

}
