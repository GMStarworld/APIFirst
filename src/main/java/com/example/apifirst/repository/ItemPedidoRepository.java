package com.example.apifirst.repository;

import com.example.apifirst.entities.ItemPedido;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
    List<ItemPedido> findByPedido_Id(UUID idPedido);
    List<ItemPedido> findByProducto_Id(UUID idProducto);

    @Query("SELECT SUM(itemped.cantidad) FROM ItemPedido itemped WHERE itemped.producto.id=?1")
    Integer findBySumaProd(UUID productoID);
}
