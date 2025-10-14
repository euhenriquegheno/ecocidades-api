package com.ecocidades.esg.repository;

import com.ecocidades.esg.model.FeedbackCidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackCidadaoRepository extends JpaRepository<FeedbackCidadao, Long> {
}

