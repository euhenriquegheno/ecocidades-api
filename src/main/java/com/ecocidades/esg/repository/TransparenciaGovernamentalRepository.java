package com.ecocidades.esg.repository;

import com.ecocidades.esg.model.TransparenciaGovernamental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransparenciaGovernamentalRepository extends JpaRepository<TransparenciaGovernamental, Long> {
}

