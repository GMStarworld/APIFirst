package com.example.apifirst.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="items_pedidos")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer cantidad;
    private Float precio;

    @ManyToOne
    @JoinColumn(name = "FK_PEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "FK_PRODUCT")
    private Product producto;
}
