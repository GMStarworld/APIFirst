package com.example.apifirst.service;

import com.example.apifirst.dto.PagoDto;
import com.example.apifirst.dto.PagoToSaveDto;
import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.entities.Pago;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PagoMetodo;
import com.example.apifirst.mapper.PagoMapper;
import com.example.apifirst.repository.PagoRepository;
import com.example.apifirst.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagoServiceImp implements PagoService{
    private final PagoRepository pagoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagoMapper pagoMapper;

    public PagoServiceImp (PagoRepository pagoRepository, PagoMapper pagoMapper, PedidoRepository pedidoRepository){
        this.pagoRepository=pagoRepository;
        this.pagoMapper=pagoMapper;
        this.pedidoRepository=pedidoRepository;
    }
    @Override
    public PagoDto guardarPago(PagoToSaveDto pago) {
        Pago pagoSaved=pagoMapper.pagoToSaveDtoToEntity(pago);
        Optional<Pedido> pedidoFound=pedidoRepository.findById(pago.pedido().id());
        pagoSaved.setPedido(pedidoFound.get());
        pagoSaved=pagoRepository.save(pagoSaved);
        return pagoMapper.entityToPagoDto(pagoSaved);
    }

    @Override
    public PagoDto actualizarPago(UUID id, PagoToSaveDto pago) {
        Optional<Pago> pagoFound = pagoRepository.findById(id);

        if (pago.fechaPago() != null) pagoFound.get().setFechaPago(pago.fechaPago());
        if (pago.metodoPago() != null) pagoFound.get().setMetodoPago(pago.metodoPago());
        if (pago.totalPago() != null) pagoFound.get().setTotalPago(pago.totalPago());

        Pago pagoUpdated = pagoRepository.save(pagoFound.get());

        return pagoMapper.entityToPagoDto(pagoUpdated);
    }

    @Override
    public PagoDto buscarPagoById(UUID id) {
        Optional<Pago> pagoFound=pagoRepository.findById(id);
        return pagoMapper.entityToPagoDto(pagoFound.get());
    }

    @Override
    public void removerPago(UUID id) {
        pagoRepository.deleteById(id);
    }

    @Override
    public List<PagoDto> getAllPagos() {
        List<Pago> pagoFound=pagoRepository.findAll();
        List<PagoDto> foundToDto=new ArrayList<>();
        pagoFound.forEach(pago -> {
            PagoDto c=pagoMapper.entityToPagoDto(pago);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<PagoDto> findByFechaPagoBetween(LocalDate fechaIni, LocalDate fechaFin) {
        List<Pago> pagoFound=pagoRepository.findByFechaPagoBetween(fechaIni, fechaFin);
        List<PagoDto> foundToDto=new ArrayList<>();
        pagoFound.forEach(pago -> {
            PagoDto c=pagoMapper.entityToPagoDto(pago);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<PagoDto> findByPedido_IdAndMetodoPago(UUID id, PagoMetodo metodoPago) {
        List<Pago> pagoFound=pagoRepository.findByPedido_IdAndMetodoPago(id, metodoPago);
        List<PagoDto> foundToDto=new ArrayList<>();
        pagoFound.forEach(pago -> {
            PagoDto c=pagoMapper.entityToPagoDto(pago);
            foundToDto.add(c);
        });
        return foundToDto;
    }
}
