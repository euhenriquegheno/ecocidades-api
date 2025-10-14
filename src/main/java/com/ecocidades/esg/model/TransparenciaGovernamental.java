package com.ecocidades.esg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "transparencia_governamental")
@Data
public class TransparenciaGovernamental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String numero;
    private String objeto;
    private Date dataPublicacao;
    private String status;
    private Double valorEstimado;
    private String modalidade;
    private String referencia;
    private String orgaoAuditor;
    private Date dataAuditoria;
    private String resultado;
    // Detalhes despesa will be stored as JSON string
    @Column(columnDefinition = "TEXT")
    private String detalhesDespesaJson;
    private String evento;
    private String local;
    private Integer numeroParticipantes;
    private String fornecedor;
    private Double valor;
    private Integer vigenciaMeses;
    private String nome;
    private String empresaVencedora;
}

