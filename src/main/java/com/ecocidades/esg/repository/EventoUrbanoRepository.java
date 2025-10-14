package com.ecocidades.esg.repository;

import com.ecocidades.esg.model.EventoUrbano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoUrbanoRepository extends JpaRepository<EventoUrbano, Long> {
}

