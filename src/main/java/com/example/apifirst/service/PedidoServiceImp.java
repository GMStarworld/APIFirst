package com.example.apifirst.service;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import com.example.apifirst.mapper.PedidoMapper;
import com.example.apifirst.repository.ClienteRepository;
import com.example.apifirst.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoServiceImp implements PedidoService{
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoServiceImp(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper, ClienteRepository clienteRepository){
        this.pedidoRepository=pedidoRepository;
        this.pedidoMapper=pedidoMapper;
        this.clienteRepository=clienteRepository;
    }
    @Override
    public PedidoDto guardarPedido(PedidoToSaveDto pedido) {
        Pedido pedidoSaved=pedidoMapper.pedidoDtoToSaveToEntity(pedido);
        Optional<Cliente> clienteFound=clienteRepository.findById(pedido.cliente().id());
        pedidoSaved.setCliente(clienteFound.get());
        pedidoSaved=pedidoRepository.save(pedidoSaved);
        System.out.print(pedidoSaved);
        return pedidoMapper.entityToPedidoDto(pedidoSaved);
    }

    @Override
    public PedidoDto actualizarPedido(UUID id, PedidoToSaveDto pedido) {
        Optional<Pedido> pedidoFound = pedidoRepository.findById(id);

        if (pedido.fecha() != null) pedidoFound.get().setFecha(pedido.fecha());
        if (pedido.estado() != null) pedidoFound.get().setEstado(pedido.estado());

        Pedido pedidoUpdated = pedidoRepository.save(pedidoFound.get());

        return pedidoMapper.entityToPedidoDto(pedidoUpdated);
    }

    @Override
    public PedidoDto buscarPedidoById(UUID id) {
        Optional<Pedido> pedidoFound=pedidoRepository.findById(id);
        return pedidoMapper.entityToPedidoDto(pedidoFound.get());
    }

    @Override
    public void removerPedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<PedidoDto> getAllPedidos() {
        List<Pedido> pedidoFound=pedidoRepository.findAll();
        List<PedidoDto> foundToDto=new ArrayList<>();
        pedidoFound.forEach(pedido -> {
            PedidoDto c=pedidoMapper.entityToPedidoDto(pedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<PedidoDto> findByFechaBetween(LocalDate fechaIni, LocalDate fechaFin) {
        List<Pedido> pedidoFound=pedidoRepository.findByFechaBetween(fechaIni, fechaFin);
        List<PedidoDto> foundToDto=new ArrayList<>();
        pedidoFound.forEach(pedido -> {
            PedidoDto c=pedidoMapper.entityToPedidoDto(pedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<PedidoDto> findByCliente_IdAndEstado(UUID id, PedidoStatus estado){
        List<Pedido> pedidoFound=pedidoRepository.findByCliente_IdAndEstado(id, estado);
        List<PedidoDto> foundToDto=new ArrayList<>();
        pedidoFound.forEach(pedido -> {
            PedidoDto c=pedidoMapper.entityToPedidoDto(pedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<PedidoDto> findByClienteWithItems(UUID clienteID){
        List<Pedido> pedidoFound=pedidoRepository.findByClienteWithItems(clienteID);
        List<PedidoDto> foundToDto=new ArrayList<>();
        pedidoFound.forEach(pedido -> {
            PedidoDto c=pedidoMapper.entityToPedidoDto(pedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }
}
