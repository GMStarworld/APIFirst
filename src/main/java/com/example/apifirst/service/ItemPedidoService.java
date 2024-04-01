package com.example.apifirst.service;

import com.example.apifirst.dto.ItemPedidoDto;
import com.example.apifirst.dto.ItemPedidoToSaveDto;
import com.example.apifirst.entities.ItemPedido;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoService {
    ItemPedidoDto guardarItemPedido(ItemPedidoToSaveDto itemPedido);

    ItemPedidoDto actualizarItemPedido(UUID id, ItemPedidoToSaveDto itemPedido);

    ItemPedidoDto buscarItemPedidoById(UUID id);

    void removerItemPedido(UUID id);

    List<ItemPedidoDto> getAllItems();

    List<ItemPedidoDto> findByPedido_Id(UUID idPedido);
    List<ItemPedidoDto> findByProducto_Id(UUID idProducto);
    Integer findBySumaProd(UUID productoID);
}
