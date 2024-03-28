package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.ItemPedido;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PedidoRepositoryTest extends AbstractIntegrationDBTest {

    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private ItemPedidoRepository itemPedidoRepository;
    private Pedido pedido, pedido1;
    private Cliente cliente, cliente1;

    @Autowired
    public PedidoRepositoryTest(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ItemPedidoRepository itemPedidoRepository){
        this.pedidoRepository=pedidoRepository;
        this.clienteRepository=clienteRepository;
        this.itemPedidoRepository=itemPedidoRepository;
    }

    @BeforeEach
    void setUp() {

        pedidoRepository.deleteAll();

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

        cliente1=clienteRepository.save(Cliente.builder()
                .nombre("Juancho")
                .email("Juanchodiaz@gmail.com")
                .direccion("La zona patatera")
                .build()
        );

        pedido1=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2020-07-22"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(cliente1)
                .build()
        );
    }

    @Test
    void createPedido(){
        //Given
        //When
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        Pedido pedidocreated=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2022-01-01"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(clienteCreated)
                .build()
        );
        //Then
        assertThat(pedidocreated).isNotNull();
        assertThat(pedidocreated.getCliente()).isEqualTo(clienteCreated);
    }

    @Test
    void editPedido(){
        //Given
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        Pedido pedidocreated=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2022-01-01"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(clienteCreated)
                .build()
        );
        //When
        Pedido pedidoUpdated=pedidocreated;
        pedidoUpdated.setEstado(PedidoStatus.fromString("ENVIADO"));
        pedidoUpdated.setFecha(LocalDate.parse("2023-01-01"));
        pedidoUpdated=pedidoRepository.save(pedidoUpdated);
        //Then
        assertThat(pedidoUpdated).isNotNull();
        assertThat(pedidoUpdated.getCliente()).isEqualTo(clienteCreated);
    }

    @Test
    void readPedido(){
        //Given
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        Pedido pedidocreated=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2022-01-01"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(clienteCreated)
                .build()
        );
        //When
        Optional<Pedido> pedidoFound=pedidoRepository.findById(pedidocreated.getId());
        //Then
        assertThat(pedidoFound).isPresent();
        assertThat(pedidoFound.get().getFecha()).isEqualTo(pedidocreated.getFecha());
    }

    @Test
    void findByFechaBetween() {
        //Given
        //When
        List<Pedido> pedidoFound=pedidoRepository.findByFechaBetween(LocalDate.parse("2018-01-01"), LocalDate.parse("2021-01-01"));
        //Then
        assertThat(pedidoFound).isNotNull();
        assertThat(pedidoFound.get(0).getCliente()).isEqualTo(cliente1);
    }

    @Test
    void findByCliente_IdAndEstado() {
        //Given
        //When
        List<Pedido> pedidoFound=pedidoRepository.findByCliente_IdAndEstado(cliente.getId(), PedidoStatus.fromString("PENDIENTE"));
        //Then
        assertThat(pedidoFound).isNotNull();
        assertThat(pedidoFound.get(0).getCliente()).isEqualTo(cliente);
    }

    @Test
    void findByClienteWithItems() {
        //Given
        ItemPedido item=itemPedidoRepository.save(ItemPedido.builder()
                .cantidad(20)
                .precio((float)20000)
                .pedido(pedido).
                build()
        );
        ItemPedido item1=itemPedidoRepository.save(ItemPedido.builder()
                .cantidad(10)
                .precio((float)50000)
                .pedido(pedido1)
                .build()
        );
        //When
        List<Pedido> pedidoFound=pedidoRepository.findByClienteWithItems(cliente1.getId());
        //Then
        assertThat(pedidoFound).isNotNull();
        assertThat(pedidoFound.get(0).getCliente()).isEqualTo(cliente1);
    }
}