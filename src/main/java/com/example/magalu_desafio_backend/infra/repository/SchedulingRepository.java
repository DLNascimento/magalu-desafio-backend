package com.example.magalu_desafio_backend.infra.repository;

import com.example.magalu_desafio_backend.infra.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {




}


