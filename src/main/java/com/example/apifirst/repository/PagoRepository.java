package com.example.apifirst.repository;

import com.example.apifirst.entities.Pago;
import com.example.apifirst.enumerations.PagoMetodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PagoRepository extends JpaRepository<Pago, UUID> {
    List<Pago> findByFechaPagoBetween(LocalDate fechaIni, LocalDate fechaFin);
    List<Pago> findByPedido_IdAndMetodoPago(UUID id, PagoMetodo metodoPago);
}
