package com.ecocidades.esg.repository;

import com.ecocidades.esg.model.IniciativaSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IniciativaSocialRepository extends JpaRepository<IniciativaSocial, Long> {
}

