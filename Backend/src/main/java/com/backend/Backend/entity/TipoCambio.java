package com.backend.Backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_cambio")
public class TipoCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaConsulta;
    private LocalDate fechaTipoCambio;
    private BigDecimal tipoCambio;
    private String origenApi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public LocalDate getFechaTipoCambio() {
        return fechaTipoCambio;
    }

    public void setFechaTipoCambio(LocalDate fechaTipoCambio) {
        this.fechaTipoCambio = fechaTipoCambio;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getOrigenApi() {
        return origenApi;
    }

    public void setOrigenApi(String origenApi) {
        this.origenApi = origenApi;
    }
}