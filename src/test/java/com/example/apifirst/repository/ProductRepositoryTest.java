package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProductRepositoryTest extends AbstractIntegrationDBTest {

    private ProductRepository productRepository;
    private Product producto, producto1;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository=productRepository;
    }

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        producto=productRepository.save(Product.builder()
                .nombre("Abanico desechable")
                .precio((float)15000)
                .stock((long)20)
                .build()
        );

        producto1=productRepository.save(Product.builder()
                .nombre("Automata finito")
                .precio((float)10000)
                .stock((long)0)
                .build()
        );
    }

    @Test
    void givenNewProducto_WhenProductoIsAdded_ThenProductoIsFound(){
        //Given a producto
        //When
        Product productoCreated=productRepository.save(Product.builder()
                .nombre("Grandes éxitos del vallenato")
                .precio((float)35000)
                .stock((long)2)
                .build()
        );
        //Then
        assertThat(productoCreated.getId()).isNotNull();
        assertThat(productoCreated.getNombre()).matches("Grandes éxitos del vallenato");
    }

    @Test
    void givenProductoId_WhenProductoIdMatches_ThenProductoIsFound(){
        //Given a producto
        Product productoCreated=productRepository.save(Product.builder()
                .nombre("Grandes éxitos del vallenato")
                .precio((float)35000)
                .stock((long)2)
                .build()
        );
        //When
        Optional<Product> productoFound=productRepository.findById(productoCreated.getId());
        //Then
        assertThat(productoFound).isPresent();
        assertThat(productoFound.get().getNombre()).matches("Grandes éxitos del vallenato");
    }

    @Test
    void givenProductoData_WhenProductoIsModified_ThenChangesAreFound(){
        //Given a producto
        Product productoCreated=productRepository.save(Product.builder()
                .nombre("Grandes éxitos del vallenato")
                .precio((float)35000)
                .stock((long)2)
                .build()
        );
        //When
        Product productoUpdated=productoCreated;
        productoUpdated.setNombre("Bachata extrema");
        productoUpdated.setStock((long)5);
        productoUpdated=productRepository.save(productoUpdated);
        //Then
        assertThat(productoUpdated.getNombre()).matches("Bachata extrema");
        assertThat(productoUpdated.getStock()).isEqualTo(5);
    }

    @Test
    void givenProductoId_WhenProductoMatches_ThenProductoIsNotFound(){
        //Given a producto
        Product productoCreated=productRepository.save(Product.builder()
                .nombre("Grandes éxitos del vallenato")
                .precio((float)35000)
                .stock((long)2)
                .build()
        );
        //When
        productRepository.deleteById(productoCreated.getId());
        Optional<Product> productoFound=productRepository.findById(productoCreated.getId());
        //Then
        assertThat(productoFound).isNotPresent();
    }

    @Test
    void findByNombre() {
        //Given
        //When
        List<Product> productoFound=productRepository.findByNombre("Abanico desechable");
        //Then
        assertThat(productoFound).isNotNull();
        assertThat(productoFound.get(0).getId()).isEqualTo(producto.getId());
    }

    @Test
    void findByInStock() {
        //Given
        //When
        List<Product> productoFound=productRepository.findByInStock();
        //Then
        assertThat(productoFound.get(0)).isNotNull();
        assertThat(productoFound.get(0)).isEqualTo(producto);
    }

    @Test
    void findByStockLessThanAndPrecioLessThan() {
        //Given
        //When
        List<Product> productoFound=productRepository.findByStockLessThanAndPrecioLessThan((long)10,(float)12000);
        //Then
        assertThat(productoFound.get(0)).isNotNull();
        assertThat(productoFound.get(0)).isEqualTo(producto1);
    }
}