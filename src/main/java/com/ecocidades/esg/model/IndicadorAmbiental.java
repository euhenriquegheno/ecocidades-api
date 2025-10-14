package com.ecocidades.esg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "indicadores_ambientais")
@Data
public class IndicadorAmbiental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String localizacao;
    private Date dataHora;
    private String unidade;
    private String status;
    private String observacoes;
    // Medicoes will be stored as JSON string due to varying structure
    @Column(columnDefinition = "TEXT")
    private String medicoesJson;
}

