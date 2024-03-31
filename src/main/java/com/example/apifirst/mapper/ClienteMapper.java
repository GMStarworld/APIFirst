package com.example.apifirst.mapper;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;
import com.example.apifirst.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente clienteDtoToEntity(ClienteDto clienteDto);
    @Mapping(target="pedidos", expression="java(new ArrayList<>())")
    ClienteDto entityToDto(Cliente cliente);

    @Mapping(target ="id", ignore = true)
    @Mapping(target="pedidos", expression="java(new ArrayList<>())")
    Cliente clienteToSaveDtoToEntity(ClienteToSaveDto clienteToSaveDto);

    ClienteToSaveDto entityToClienteToSaveDto(Cliente cliente);
}
