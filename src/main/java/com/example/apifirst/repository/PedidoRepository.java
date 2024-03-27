package com.example.apifirst.repository;

import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByFechaBetween(LocalDate fechaIni, LocalDate fechaFin);
    List<Pedido> findByCliente_IdAndEstado(UUID id, PedidoStatus estado);

    @Query("SELECT ped FROM Pedido ped JOIN FETCH ped.item_pedido WHERE ped.cliente.id = ?1")
    List<Pedido> findByClienteWithItems(UUID clienteID);
}
