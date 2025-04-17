package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "barrios")
@Data
public class Barrios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_barrio")
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ciudad", nullable = false)
    private Ciudades ciudad;
    
    @NotBlank
    @Size(max = 80)
    @Column(name = "descripcion", nullable = false, length = 80)
    private String descripcion;
}
