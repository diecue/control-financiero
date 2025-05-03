package com.example.demo.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cobros")
@Data
public class Cobros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobro")
    private Integer id;

    @ManyToOne @JoinColumn(name = "id_factura")
    private Facturas factura;          // <-- asume entidad Facturas existente

    @ManyToOne @JoinColumn(name = "id_cliente")
    private Clientes cliente;          // <-- entidad Clientes ya creada

    @ManyToOne @JoinColumn(name = "id_tipo_pago")
    private TipoPagos tipoPago;

    @ManyToOne @JoinColumn(name = "id_banco")
    private Bancos banco;              // para efectivo / tarjeta

    @ManyToOne @JoinColumn(name = "id_tarjeta")
    private Tarjetas tarjeta;

    @ManyToOne @JoinColumn(name = "id_cheque")
    private Cheques cheque;

    private BigDecimal monto;
    private BigDecimal tc;             // tipo de cambio si aplica

    @Column(length = 20)
    private String estado = "PENDIENTE";

    private LocalDate fechaRegistro = LocalDate.now();
}
