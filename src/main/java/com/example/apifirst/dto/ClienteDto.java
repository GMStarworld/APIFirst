package com.example.apifirst.dto;

import com.example.apifirst.entities.Pedido;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record ClienteDto(
        UUID id,
        String nombre,
        String email,
        String direccion,
        List<Pedido> pedidos
){
    public List<Pedido> pedidos(){
        return Collections.unmodifiableList(pedidos);
    }
}
