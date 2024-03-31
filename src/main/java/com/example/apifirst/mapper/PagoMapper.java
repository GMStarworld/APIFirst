package com.example.apifirst.mapper;

import com.example.apifirst.dto.PagoDto;
import com.example.apifirst.dto.PagoToSaveDto;
import com.example.apifirst.entities.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    Pago pagoDtoToEntity(PagoDto pagoDto);

    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target ="pedido.pago", ignore = true)
    @Mapping(target ="pedido.cliente", ignore = true)
    PagoDto entityToPagoDto(Pago pago);

    @Mapping(target ="id", ignore = true)
    @Mapping(target="pedido.itemPedido", expression = "java(new ArrayList<>())")
    @Mapping(target="pedido.detalleEnvio", ignore = true)
    @Mapping(target ="pedido.pago", ignore = true)
    @Mapping(target ="pedido.cliente", ignore = true)
    Pago pagoToSaveDtoToEntity(PagoToSaveDto pagoToSaveDto);

    PagoToSaveDto entityToPagoToSaveDto(Pago pago);
}
