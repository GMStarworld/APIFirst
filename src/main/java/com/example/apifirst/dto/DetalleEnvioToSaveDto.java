package com.example.apifirst.dto;

import com.example.apifirst.entities.Pedido;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.UUID;

public record DetalleEnvioToSaveDto(
        String direccion,
        String transportadora,
        Integer numeroGuia,

        PedidoDto pedido
) {
}
