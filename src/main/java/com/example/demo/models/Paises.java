package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "paises")
@Data
public class Paises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Integer id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "descripcion", nullable = false, length = 80, unique = true)
    private String descripcion;
}
