package com.ecocidades.esg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "eventos_urbanos")
@Data
public class EventoUrbano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
    private Date data;
    private String local;
    // Organizadores will be stored as JSON string
    @Column(columnDefinition = "TEXT")
    private String organizadoresJson;
    private String status;
    private Integer oleoColetadoLitros;
    // Impacto will be stored as JSON string
    @Column(columnDefinition = "TEXT")
    private String impactoJson;
    private String causa;
    private Integer atendimentosRealizados;
}

