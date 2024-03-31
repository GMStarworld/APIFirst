package com.example.apifirst.dto;

import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.DetalleEnvio;
import com.example.apifirst.entities.ItemPedido;
import com.example.apifirst.entities.Pago;
import com.example.apifirst.enumerations.PedidoStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public record PedidoDto(
        UUID id,
        LocalDate fecha,
        PedidoStatus estado,
        ClienteDto cliente,
        List<ItemPedido> itemPedido,
        DetalleEnvioDto detalleEnvio,
        PagoDto pago
) {
    public List<ItemPedido> itemPedido(){
        return Collections.unmodifiableList(itemPedido);
    }
}
