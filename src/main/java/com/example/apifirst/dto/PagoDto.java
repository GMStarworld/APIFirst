package com.example.apifirst.dto;

import com.example.apifirst.enumerations.PagoMetodo;

import java.time.LocalDate;
import java.util.UUID;

public record PagoDto(
        UUID id,
        Float totalPago,
        LocalDate fechaPago,
        PagoMetodo metodoPago,
        PedidoDto pedido
) {
}
