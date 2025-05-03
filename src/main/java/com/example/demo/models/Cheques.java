package com.example.demo.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cheques")
@Data
public class Cheques {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cheque")
    private Integer id;

    @ManyToOne @JoinColumn(name = "id_banco", nullable = false)
    private Bancos banco;

    @Column(length = 30, nullable = false)
    private String numeroCheque;

    @Column(length = 80, nullable = false)
    private String titular;

    private LocalDate fechaEmision;
    private LocalDate fechaPago;

    private BigDecimal monto;

    @Column(length = 20)
    private String estado = "PENDIENTE";
}
