package com.example.apifirst.mapper;

import com.example.apifirst.dto.ItemPedidoDto;
import com.example.apifirst.dto.ItemPedidoToSaveDto;
import com.example.apifirst.entities.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedido itemPedidoDtoToEntity(ItemPedidoDto itemPedidoDto);

    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.pago", ignore = true)
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target="pedido.cliente", ignore=true)
    @Mapping(target = "producto.item_pedido", expression="java(new ArrayList<>())")
    ItemPedidoDto entityToItemPedidoDto(ItemPedido itemPedido);

    @Mapping(target="id", ignore=true)
    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.pago", ignore = true)
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target="pedido.cliente", ignore=true)
    @Mapping(target = "producto.item_pedido", expression="java(new ArrayList<>())")
    ItemPedido itemPedidoToSaveDtoToEntity(ItemPedidoToSaveDto itemPedidoToSaveDto);

    ItemPedidoToSaveDto entityToItemToSaveDto(ItemPedido itemPedido);
}
