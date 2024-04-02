package com.example.apifirst.mapper;

import com.example.apifirst.dto.DetalleEnvioDto;
import com.example.apifirst.dto.DetalleEnvioToSaveDto;
import com.example.apifirst.entities.DetalleEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleEnvioMapper {
    DetalleEnvio detalleEnvioDtoToEntity(DetalleEnvioDto detalleEnvioDto);

    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.pago", ignore = true)
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target="pedido.cliente", ignore=true)
    DetalleEnvioDto entityToDto(DetalleEnvio detalleEnvio);

    @Mapping(target="id", ignore=true)
    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.pago", ignore = true)
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target="pedido.cliente", ignore=true)
    DetalleEnvio detalleEnvioToSaveDtoToEntity(DetalleEnvioToSaveDto detalleEnvioToSaveDto);

    DetalleEnvioToSaveDto entityToDetalleEnvioToSaveDto(DetalleEnvio detalleEnvio);
}
