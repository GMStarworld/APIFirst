package com.example.apifirst.dto;

import java.util.UUID;

public record ItemPedidoToSaveDto (
        Integer cantidad,
        Float precio,
        PedidoDto pedido,
        ProductDto producto
){
}
