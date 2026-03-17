package com.ecocidades.esg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "feedback_cidadao")
@Data
public class FeedbackCidadao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String autor;
  private String tipo;
  @Column(columnDefinition = "TEXT")
  private String descricao;
  private Date data;
  @Enumerated(EnumType.STRING)
  private StatusFeedbackCidadao status;

  public FeedbackCidadao(String autor, String tipo, String descricao) {
    //TODO Auto-generated constructor stub
  }
    
}

