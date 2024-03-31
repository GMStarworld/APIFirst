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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itemPedido;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private DetalleEnvio detalleEnvio;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pago pago;
}
