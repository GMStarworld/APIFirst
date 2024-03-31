package com.example.apifirst.service;

import com.example.apifirst.dto.PagoDto;
import com.example.apifirst.dto.PagoToSaveDto;
import com.example.apifirst.entities.Pago;
import com.example.apifirst.enumerations.PagoMetodo;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PagoService {
    PagoDto guardarPago(PagoToSaveDto pago);

    PagoDto actualizarPago(UUID id, PagoToSaveDto pago);

    PagoDto buscarPagoById(UUID id);

    void removerPago(UUID id);

    List<PagoDto> getAllPagos();

    List<PagoDto> findByFechaPagoBetween(LocalDate fechaIni, LocalDate fechaFin);
    List<PagoDto> findByPedido_IdAndMetodoPago(UUID id, PagoMetodo metodoPago);
}
