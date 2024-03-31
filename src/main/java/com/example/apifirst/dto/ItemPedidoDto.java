package com.example.apifirst.dto;

import com.example.apifirst.entities.Pedido;
import com.example.apifirst.entities.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record ItemPedidoDto(
        UUID id,
        Integer cantidad,
        Float precio,
        PedidoDto pedido,
        ProductDto producto
) {
}
