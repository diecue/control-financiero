package com.example.demo.models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facturas")
@Data @NoArgsConstructor @AllArgsConstructor
public class Facturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;

    @Column(name = "id_empleado")
    private String id_empleado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursales sucursal;

    @Column(name = "id_timbrado", nullable = false)
    private String id_timbrado;


    private LocalDate fecha;

    /** ‘ACT’, ‘ANU’, etc. */
    @Column(length = 3, nullable = false)
    private String estado;

    private Integer nro1;
    private Integer nro2;
    private Integer nro3;


    @Column(name = "id_tipo_documento")
    private String id_tipo_documento;

    /* Si querés mapear el resto de columnas, agregalas con sus anotaciones. */
}
