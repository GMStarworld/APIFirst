package com.example.apifirst.dto;

import com.example.apifirst.entities.ItemPedido;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record ProductDto(
        UUID id,
        String nombre,
        Float precio,
        Long stock,
        List<ItemPedido> item_pedido
) {
    public List<ItemPedido> item_pedido(){return Collections.unmodifiableList(item_pedido);}
}
