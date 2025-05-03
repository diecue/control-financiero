package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "departamentos")
@Data
public class Departamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Integer id;

    // Relación con Paises
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pais", nullable = false)
    private Paises pais;

    @NotBlank
    @Size(max = 80)
    @Column(name = "descripcion", nullable = false, length = 80)
    private String descripcion;
}
