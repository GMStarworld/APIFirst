package com.example.apifirst.mapper;

import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "detalleEnvio",  ignore = true)
    @Mapping(target = "itemPedido",  ignore = true)
    @Mapping(target = "cliente.pedidos",  ignore = true)
    Pedido pedidoDtoToEntity(PedidoDto pedidoDto);


    PedidoDto entityToPedidoDto(Pedido pedido);

    @Mapping(target ="id", ignore = true)
    @Mapping(target="itemPedido", expression="java(new ArrayList<>())")
    @Mapping(target="cliente.pedidos", expression="java(new ArrayList<>())")
    @Mapping(target="pago", expression = "java(new Pago())")
    @Mapping(target="detalleEnvio", expression = "java(new DetalleEnvio())")
    Pedido pedidoDtoToSaveToEntity(PedidoToSaveDto pedidoToSaveDto);

    PedidoToSaveDto entityToPedidoToSaveDto(Pedido pedido);
}
