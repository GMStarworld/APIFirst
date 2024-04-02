package com.example.apifirst.service;

import com.example.apifirst.dto.DetalleEnvioDto;
import com.example.apifirst.dto.DetalleEnvioToSaveDto;
import com.example.apifirst.entities.DetalleEnvio;
import com.example.apifirst.enumerations.PedidoStatus;

import java.util.List;
import java.util.UUID;

public interface DetalleEnvioService {
    DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioToSaveDto detalleEnvioToSaveDto);
    DetalleEnvioDto actualizarDetalleEnvio(UUID id, DetalleEnvioToSaveDto detalleEnvioToSaveDto);
    DetalleEnvioDto buscarDetalleEnvioById(UUID id);
    void removerDetalleEnvio(UUID id);
    List<DetalleEnvioDto> getAllDetalleEnvio();
    List<DetalleEnvioDto> findByPedido_Id(UUID idPedido);
    List<DetalleEnvioDto> findByTransportadora(String transportadora);
    List<DetalleEnvioDto> findByPedido_Estado(PedidoStatus estadoPedido);
}
