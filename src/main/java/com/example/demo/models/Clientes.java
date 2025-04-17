package com.example.demo.models;

//Importaciones de JPA
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

//Importaciones de validación
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;

    // Mapeo de la llave foránea a barrios
    // Se asume que la entidad Barrios ya existe y está en com.example.demo.models
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_barrio", nullable = false)
    private Barrios barrio;

    @NotBlank
    @Size(max = 100)
    @Column(name = "razon_social", nullable = false, length = 100)
    private String razonSocial;

    @NotBlank
    @Size(max = 20)
    @Column(name = "ruc", nullable = false, length = 20, unique = true)
    private String ruc;

    @NotBlank
    @Size(max = 100)
    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @NotBlank
    @Size(max = 20)
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    // El campo tipo_cliente es opcional y es de tamaño 3 (bpchar(3))
    @Size(max = 3)
    @Column(name = "tipo_cliente", length = 3)
    private String tipoCliente;
}
