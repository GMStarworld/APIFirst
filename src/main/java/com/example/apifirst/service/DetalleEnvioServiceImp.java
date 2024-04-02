package com.example.apifirst.service;

import com.example.apifirst.dto.DetalleEnvioDto;
import com.example.apifirst.dto.DetalleEnvioToSaveDto;
import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.DetalleEnvio;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import com.example.apifirst.mapper.DetalleEnvioMapper;
import com.example.apifirst.repository.DetalleEnvioRepository;
import com.example.apifirst.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DetalleEnvioServiceImp implements DetalleEnvioService{
    private final DetalleEnvioRepository detalleEnvioRepository;
    private final PedidoRepository pedidoRepository;
    private final DetalleEnvioMapper detalleEnvioMapper;

    public DetalleEnvioServiceImp(DetalleEnvioRepository detalleEnvioRepository, DetalleEnvioMapper detalleEnvioMapper, PedidoRepository pedidoRepository){
        this.detalleEnvioRepository=detalleEnvioRepository;
        this.detalleEnvioMapper=detalleEnvioMapper;
        this.pedidoRepository=pedidoRepository;
    }
    @Override
    public DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto) {
        DetalleEnvio detalleEnvioSaved=detalleEnvioMapper.detalleEnvioToSaveDtoToEntity(detalleEnvioToSaveDto);
        Optional<Pedido> pedidoFound=pedidoRepository.findById(detalleEnvioToSaveDto.pedido().id());
        detalleEnvioSaved.setPedido(pedidoFound.get());
        detalleEnvioSaved=detalleEnvioRepository.save(detalleEnvioSaved);
        return detalleEnvioMapper.entityToDto(detalleEnvioSaved);
    }

    @Override
    public DetalleEnvioDto actualizarDetalleEnvio(UUID id, DetalleEnvioToSaveDto detalleEnvioToSaveDto) {
        Optional<DetalleEnvio> detalleEnvioFound = detalleEnvioRepository.findById(id);

        if (detalleEnvioToSaveDto.direccion() != null) detalleEnvioFound.get().setDireccion(detalleEnvioToSaveDto.direccion());
        if (detalleEnvioToSaveDto.transportadora() != null) detalleEnvioFound.get().setTransportadora(detalleEnvioToSaveDto.transportadora());
        if (detalleEnvioToSaveDto.numeroGuia() != null) detalleEnvioFound.get().setNumeroGuia(detalleEnvioToSaveDto.numeroGuia());

        DetalleEnvio detalleEnvioUpdated = detalleEnvioRepository.save(detalleEnvioFound.get());

        return detalleEnvioMapper.entityToDto(detalleEnvioUpdated);
    }

    @Override
    public DetalleEnvioDto buscarDetalleEnvioById(UUID id) {
        Optional<DetalleEnvio> detalleEnvioFound=detalleEnvioRepository.findById(id);
        return detalleEnvioMapper.entityToDto(detalleEnvioFound.get());
    }

    @Override
    public void removerDetalleEnvio(UUID id) {
        detalleEnvioRepository.deleteById(id);
    }

    @Override
    public List<DetalleEnvioDto> getAllDetalleEnvio() {
        List<DetalleEnvio> detalleEnvioFound=detalleEnvioRepository.findAll();
        List<DetalleEnvioDto> foundToDto=new ArrayList<>();
        detalleEnvioFound.forEach(detalleEnvio -> {
            DetalleEnvioDto c=detalleEnvioMapper.entityToDto(detalleEnvio);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<DetalleEnvioDto> findByPedido_Id(UUID idPedido) {
        List<DetalleEnvio> detalleEnvioFound=detalleEnvioRepository.findByPedido_Id(idPedido);
        List<DetalleEnvioDto> foundToDto=new ArrayList<>();
        detalleEnvioFound.forEach(detalleEnvio -> {
            DetalleEnvioDto c=detalleEnvioMapper.entityToDto(detalleEnvio);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<DetalleEnvioDto> findByTransportadora(String transportadora) {
        List<DetalleEnvio> detalleEnvioFound=detalleEnvioRepository.findByTransportadora(transportadora);
        List<DetalleEnvioDto> foundToDto=new ArrayList<>();
        detalleEnvioFound.forEach(detalleEnvio -> {
            DetalleEnvioDto c=detalleEnvioMapper.entityToDto(detalleEnvio);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<DetalleEnvioDto> findByPedido_Estado(PedidoStatus estadoPedido) {
        List<DetalleEnvio> detalleEnvioFound=detalleEnvioRepository.findByPedido_Estado(estadoPedido);
        List<DetalleEnvioDto> foundToDto=new ArrayList<>();
        detalleEnvioFound.forEach(detalleEnvio -> {
            DetalleEnvioDto c=detalleEnvioMapper.entityToDto(detalleEnvio);
            foundToDto.add(c);
        });
        return foundToDto;
    }
}
