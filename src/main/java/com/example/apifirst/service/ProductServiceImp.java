package com.example.apifirst.service;

import com.example.apifirst.dto.ProductDto;
import com.example.apifirst.dto.ProductToSaveDto;
import com.example.apifirst.entities.Product;
import com.example.apifirst.mapper.ProductMapper;
import com.example.apifirst.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImp implements ProductService{
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository=productRepository;
        this.productMapper=productMapper;
    }
    @Override
    public ProductDto guardarProducto(ProductToSaveDto product) {
        Product productGuardado=productRepository.save(productMapper.productToSaveDtoToEntity(product));
        return productMapper.entityToDto(productGuardado);
    }

    @Override
    public ProductDto actualizarProduct(UUID id, ProductToSaveDto product) {
        Optional<Product> productFound = productRepository.findById(id);

        if (product.nombre() != null) productFound.get().setNombre(product.nombre());
        if (product.precio() != null) productFound.get().setPrecio(product.precio());
        if (product.stock() != null) productFound.get().setStock(product.stock());

        Product productUpdated = productRepository.save(productFound.get());

        return productMapper.entityToDto(productUpdated);
    }

    @Override
    public ProductDto buscarProductById(UUID id) {
        Optional<Product> productFound=productRepository.findById(id);
        return productMapper.entityToDto(productFound.get());
    }

    @Override
    public void removerProduct(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> productFound=productRepository.findAll();
        List<ProductDto> foundToDto=new ArrayList<>();
        productFound.forEach(product -> {
            ProductDto c=productMapper.entityToDto(product);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ProductDto> findByNombre(String nombre) {
        List<Product> productFound=productRepository.findByNombre(nombre);
        List<ProductDto> foundToDto=new ArrayList<>();
        productFound.forEach(product -> {
            ProductDto c=productMapper.entityToDto(product);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ProductDto> findByInStock() {
        List<Product> productFound=productRepository.findByInStock();
        List<ProductDto> foundToDto=new ArrayList<>();
        productFound.forEach(product -> {
            ProductDto c=productMapper.entityToDto(product);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ProductDto> findByStockLessThanAndPrecioLessThan(Long stock, Float precio) {
        List<Product> productFound=productRepository.findByStockLessThanAndPrecioLessThan(stock, precio);
        List<ProductDto> foundToDto=new ArrayList<>();
        productFound.forEach(product -> {
            ProductDto c=productMapper.entityToDto(product);
            foundToDto.add(c);
        });
        return foundToDto;
    }
}
