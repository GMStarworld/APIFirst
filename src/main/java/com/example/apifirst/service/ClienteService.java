package com.example.apifirst.service;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteService {
    ClienteDto guardarCliente(ClienteToSaveDto cliente);

    ClienteDto actualizarCliente(UUID id, ClienteToSaveDto cliente);

    ClienteDto buscarClienteById(UUID id);

    void removerCliente(UUID id);

    List<ClienteDto> getAllClients();

    List<ClienteDto> findByEmail(String email);

    List<ClienteDto> findByDireccion(String direccion);

    List<ClienteDto> findByNombreStartingWith(String nombre);
}