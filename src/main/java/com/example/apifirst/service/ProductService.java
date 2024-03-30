package com.example.apifirst.service;


import com.example.apifirst.dto.ProductDto;
import com.example.apifirst.dto.ProductToSaveDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto guardarProducto(ProductToSaveDto product);

    ProductDto actualizarProduct(UUID id, ProductToSaveDto Product);

    ProductDto buscarProductById(UUID id);

    void removerProduct(UUID id);

    List<ProductDto> getAllProducts();

    List<ProductDto> findByNombre(String nombre);
    List<ProductDto> findByInStock();
    List<ProductDto> findByStockLessThanAndPrecioLessThan(Long stock, Float precio);
}
