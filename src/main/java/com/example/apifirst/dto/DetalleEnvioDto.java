package com.example.apifirst.dto;

import java.util.UUID;
public record DetalleEnvioDto(
        UUID id,
        String direccion,
        String transportadora,
        Integer numeroGuia,

        PedidoDto pedido
) {
}
