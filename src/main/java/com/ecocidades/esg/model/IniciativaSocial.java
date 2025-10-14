package com.ecocidades.esg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "iniciativas_sociais")
@Data
public class IniciativaSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String area;
    private String publicoAlvo;
    private Date dataInicio;
    private Date dataFim;
    private String status;
    // Parceiros will be stored as JSON string or a separate many-to-many table if more complex
    @Column(columnDefinition = "TEXT")
    private String parceirosJson;
}

