package com.example.magalu_desafio_backend.controller;

import com.example.magalu_desafio_backend.business.dto.SchedulingDTO;
import com.example.magalu_desafio_backend.business.dto.SchedulingOutDTO;
import com.example.magalu_desafio_backend.business.service.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;


    @PostMapping
    public ResponseEntity<SchedulingOutDTO> newCommunicationSchedule(@RequestBody SchedulingDTO schedulingDTO){

        return ResponseEntity.ok(schedulingService.newCommunicationSchedule(schedulingDTO));

    }

    @GetMapping
    public ResponseEntity<SchedulingOutDTO> getCommunicationScheduleById(@RequestParam("id") Long id){
        return ResponseEntity.ok(schedulingService.getCommunicationSchedules(id));
    }

}
