package com.example.apifirst.entities;

import com.example.apifirst.enumerations.PedidoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private PedidoStatus estado;

    @ManyToOne
    @JoinColumn(name = "FK_CLIENTE", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedido;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;

    @OneToOne(mappedBy = "pedido")
    private Pago pago;
}
