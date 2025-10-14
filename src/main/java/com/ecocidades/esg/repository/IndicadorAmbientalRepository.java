package com.ecocidades.esg.repository;

import com.ecocidades.esg.model.IndicadorAmbiental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicadorAmbientalRepository extends JpaRepository<IndicadorAmbiental, Long> {
}

