package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "bancos")
@Data
public class Bancos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // id_banco es serial
    @Column(name = "id_banco")
    private Integer id;

    @Size(max = 50)
    private String descripcion;

    @Size(max = 15)
    private String telefono;

    @Size(max = 200)
    private String direccion;
}
