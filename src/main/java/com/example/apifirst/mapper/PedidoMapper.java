package com.example.apifirst.mapper;

import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    Pedido pedidoDtoToEntity(PedidoDto pedidoDto);

    @Mapping(target="itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pago", ignore = true)
    @Mapping(target="detalleEnvio", ignore = true)
    @Mapping(target="cliente.pedidos", expression = "java(new ArrayList<>())")
    PedidoDto entityToPedidoDto(Pedido pedido);

    @Mapping(target ="id", ignore = true)
    @Mapping(target="itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pago", ignore = true)
    @Mapping(target="detalleEnvio", ignore = true)
    @Mapping(target="cliente.pedidos", expression = "java(new ArrayList<>())")
    Pedido pedidoDtoToSaveToEntity(PedidoToSaveDto pedidoToSaveDto);

    PedidoToSaveDto entityToPedidoToSaveDto(Pedido pedido);
}
