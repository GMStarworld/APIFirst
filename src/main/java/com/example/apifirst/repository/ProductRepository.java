package com.example.apifirst.repository;

import com.example.apifirst.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID> {
    List<Product> findByNombre(String nombre);
    @Query("SELECT prod FROM Product prod WHERE prod.stock > 0")
    List<Product> findByInStock();
    List<Product> findByStockLessThanAndPrecioLessThan(Long stock, Float precio);
}
