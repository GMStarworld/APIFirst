package com.example.apifirst.service;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.mapper.ClienteMapper;
import com.example.apifirst.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ClienteServiceImp implements ClienteService{
    private final ClienteRepository clienteRepository;
    @Autowired
    private final ClienteMapper clienteMapper;

    public ClienteServiceImp (ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository=clienteRepository;
        this.clienteMapper=clienteMapper;
    }

    @Override
    public ClienteDto guardarCliente(ClienteToSaveDto cliente) {
        Cliente clienteGuardado=clienteRepository.save(clienteMapper.clienteToSaveDtoToEntity(cliente));
        return clienteMapper.entityToDto(clienteGuardado);
    }

    @Override
    public ClienteDto actualizarCliente(UUID id, ClienteToSaveDto cliente) {
        Optional<Cliente> clienteFound = clienteRepository.findById(id);

        if (cliente.nombre() != null) clienteFound.get().setNombre(cliente.nombre());
        if (cliente.email() != null) clienteFound.get().setEmail(cliente.email());
        if (cliente.direccion() != null) clienteFound.get().setDireccion(cliente.direccion());

        Cliente clienteUpdated = clienteRepository.save(clienteFound.get());

        return clienteMapper.entityToDto(clienteUpdated);
    }

    @Override
    public ClienteDto buscarClienteById(UUID id) {
        Optional<Cliente> clienteFound=clienteRepository.findById(id);
        return clienteMapper.entityToDto(clienteFound.get());
    }

    @Override
    public void removerCliente(UUID id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteDto> getAllClients() {
        List<Cliente> clienteFound=clienteRepository.findAll();
        List<ClienteDto> foundToDto=new ArrayList<>();
        clienteFound.forEach(cliente -> {
            ClienteDto c=clienteMapper.entityToDto(cliente);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ClienteDto> findByEmail(String email) {
        List<Cliente> clienteFound=clienteRepository.findByEmail(email);
        List<ClienteDto> foundToDto=new ArrayList<>();
        clienteFound.forEach(cliente -> {
            ClienteDto c=clienteMapper.entityToDto(cliente);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ClienteDto> findByDireccion(String direccion) {
        List<Cliente> clienteFound=clienteRepository.findByDireccion(direccion);
        List<ClienteDto> foundToDto=new ArrayList<>();
        clienteFound.forEach(cliente -> {
            ClienteDto c=clienteMapper.entityToDto(cliente);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ClienteDto> findByNombreStartingWith(String nombre) {
        List<Cliente> clienteFound=clienteRepository.findByNombreStartingWith(nombre);
        List<ClienteDto> foundToDto=new ArrayList<>();
        clienteFound.forEach(cliente -> {
            ClienteDto c=clienteMapper.entityToDto(cliente);
            foundToDto.add(c);
        });
        return foundToDto;
    }
}
