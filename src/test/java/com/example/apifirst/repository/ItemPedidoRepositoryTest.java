package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.ItemPedido;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.entities.Product;
import com.example.apifirst.enumerations.PedidoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPedidoRepositoryTest extends AbstractIntegrationDBTest {

    private ItemPedidoRepository itemPedidoRepository;
    private ProductRepository productRepository;
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;

    private ItemPedido item, item1;
    private Product producto, producto1;
    private Pedido pedido, pedido1;
    private Cliente cliente, cliente1;

    @Autowired
    public ItemPedidoRepositoryTest(ItemPedidoRepository itemPedidoRepository, ProductRepository productRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository){
        this.itemPedidoRepository=itemPedidoRepository;
        this.productRepository=productRepository;
        this.pedidoRepository=pedidoRepository;
        this.clienteRepository=clienteRepository;
    }

    @BeforeEach
    void setUp() {

        cliente=clienteRepository.save(Cliente.builder()
                .nombre("Juan")
                .email("Juandiaz@gmail.com")
                .direccion("La zona bananera")
                .build()
        );

        pedido=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2024-06-12"))
                .estado(PedidoStatus.fromString("PENDIENTE"))
                .cliente(cliente)
                .build()
        );

        producto=productRepository.save(Product.builder()
                .nombre("Abanico desechable")
                .precio((float)15000)
                .stock((long)20)
                .build()
        );

        item=itemPedidoRepository.save(ItemPedido.builder()
                .cantidad(20)
                .precio((float)20000)
                .pedido(pedido)
                .producto(producto)
                .build()
        );

        cliente1=clienteRepository.save(Cliente.builder()
                .nombre("Juancho")
                .email("Juanchodiaz@gmail.com")
                .direccion("La zona patatera")
                .build()
        );

        producto1=productRepository.save(Product.builder()
                .nombre("Automata finito")
                .precio((float)10000)
                .stock((long)0)
                .build()
        );

        pedido1=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2020-07-22"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(cliente1)
                .build()
        );

        item1=itemPedidoRepository.save(ItemPedido.builder()
                .cantidad(10)
                .precio((float)50000)
                .pedido(pedido1)
                .producto(producto1)
                .build()
        );

    }

    @Test
    void findByPedido_Id() {
        List<ItemPedido> itemFound=itemPedidoRepository.findByPedido_Id(pedido.getId());
        assertThat(itemFound).isNotNull();
        assertThat(itemFound.get(0)).isEqualTo(item);
    }

    @Test
    void findByProducto_Id() {
        List<ItemPedido> itemFound=itemPedidoRepository.findByProducto_Id(producto1.getId());
        assertThat(itemFound).isNotNull();
        assertThat(itemFound.get(0)).isEqualTo(item1);
    }

    @Test
    void findBySumaProd() {
        Integer quantityFound=itemPedidoRepository.findBySumaProd(producto.getId());
        assertThat(quantityFound).isNotNull();
        assertThat(quantityFound).isEqualTo(20);
    }
}