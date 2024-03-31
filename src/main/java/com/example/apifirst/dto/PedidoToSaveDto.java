package com.example.apifirst.dto;

import com.example.apifirst.entities.Cliente;
import com.example.apifirst.enumerations.PedidoStatus;

import java.time.LocalDate;

public record PedidoToSaveDto(
        LocalDate fecha,
        PedidoStatus estado,
        ClienteDto cliente
) {
}
