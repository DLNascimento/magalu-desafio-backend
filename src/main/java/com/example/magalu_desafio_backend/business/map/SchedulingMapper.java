package com.example.magalu_desafio_backend.business.map;

import com.example.magalu_desafio_backend.business.dto.SchedulingDTO;
import com.example.magalu_desafio_backend.business.dto.SchedulingOutDTO;
import com.example.magalu_desafio_backend.infra.entity.Scheduling;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {

    Scheduling paraSchedulingEntity(SchedulingDTO schedulingDTO);
    SchedulingOutDTO paraSchedulingOutDTO(Scheduling scheduling);

}
