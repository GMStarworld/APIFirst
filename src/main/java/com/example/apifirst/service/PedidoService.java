package com.example.apifirst.service;

import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoDto guardarPedido(PedidoToSaveDto pedido);

    PedidoDto actualizarPedido(UUID id, PedidoToSaveDto pedido);

    PedidoDto buscarPedidoById(UUID id);

    void removerPedido(UUID id);

    List<PedidoDto> getAllPedidos();

    List<PedidoDto> findByFechaBetween(LocalDate fechaIni, LocalDate fechaFin);
    List<PedidoDto> findByCliente_IdAndEstado(UUID id, PedidoStatus estado);

    List<PedidoDto> findByClienteWithItems(UUID clienteID);
}
