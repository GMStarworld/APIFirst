package com.example.apifirst.controller;

import com.example.apifirst.dto.ProductDto;
import com.example.apifirst.dto.ProductToSaveDto;
import com.example.apifirst.entities.Product;
import com.example.apifirst.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class productController {
    private final ProductService productService;

    public productController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductByid(@PathVariable UUID id){
        ProductDto productFound=productService.buscarProductById(id);
        return ResponseEntity.ok().body(productFound);
    }

    @GetMapping("/search ")
    public ResponseEntity<List<ProductDto>> getProductByNombre(@RequestParam("searchTerm")String nombre){
        List<ProductDto> productFound=productService.findByNombre(nombre);
        return ResponseEntity.ok().body(productFound);
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductDto>> getProductByInStock(){
        List<ProductDto> productFound=productService.findByInStock();
        return ResponseEntity.ok().body(productFound);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductToSaveDto productToSaveDto){
        ProductDto productSaved=productService.guardarProducto(productToSaveDto);
        return ResponseEntity.ok().body(productSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable UUID id, ProductToSaveDto productToSaveDto){
        ProductDto productPut=productService.actualizarProduct(id, productToSaveDto);
        return ResponseEntity.ok().body(productPut);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id){
        productService.removerProduct(id);
        return ResponseEntity.ok().body("Producto eliminado correctamente");
    }
}
