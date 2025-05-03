package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_pagos")
@Data
public class TipoPagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pago")
    private Integer id;

    @Column(length = 30, unique = true, nullable = false)
    private String descripcion;     // EFECTIVO, TARJETA, CHEQUE…
}
