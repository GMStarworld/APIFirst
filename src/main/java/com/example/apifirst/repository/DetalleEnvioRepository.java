package com.example.apifirst.repository;

import com.example.apifirst.entities.DetalleEnvio;
import com.example.apifirst.enumerations.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, UUID> {
    List<DetalleEnvio> findByPedido_Id(UUID idPedido);
    List<DetalleEnvio> findByTransportadora(String transportadora);
    List<DetalleEnvio> findByPedido_Estado(PedidoStatus estadoPedido);
}
