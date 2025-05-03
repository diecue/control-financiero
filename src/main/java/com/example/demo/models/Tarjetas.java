package com.example.demo.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tarjetas")
@Data
public class Tarjetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Integer id;

    @ManyToOne @JoinColumn(name = "id_banco", nullable = false)
    private Bancos banco;

    @Column(length = 50, nullable = false)
    private String nombreTarjeta;          // Visa, MasterCard…

    @Column(length = 20, nullable = false)
    private String numeroEnmascarado;      // ****-1234

    @Column(length = 80, nullable = false)
    private String titular;

    private LocalDate fechaVenc;
}
