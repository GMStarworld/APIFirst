package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.Pago;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PagoMetodo;
import com.example.apifirst.enumerations.PedidoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PagoRepositoryTest extends AbstractIntegrationDBTest {

    private PagoRepository pagoRepository;
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;
    private Pago pago, pago1;
    private Pedido pedido, pedido1;
    private Cliente cliente, cliente1;

    @Autowired
    public PagoRepositoryTest(PagoRepository pagoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository){
        this.pagoRepository=pagoRepository;
        this.pedidoRepository=pedidoRepository;
        this.clienteRepository=clienteRepository;
    }

    @BeforeEach
    void setUp() {
        pagoRepository.deleteAll();

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
        pago=pagoRepository.save(Pago.builder()
                .totalPago((float)120000)
                .fechaPago(LocalDate.parse("2024-01-01"))
                .metodoPago(PagoMetodo.fromString("NEQUI"))
                .pedido(pedido)
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
        pago1=pagoRepository.save(Pago.builder()
                .totalPago((float)190000)
                .fechaPago(LocalDate.parse("2020-06-15"))
                .metodoPago(PagoMetodo.fromString("DAVIPLATA"))
                .pedido(pedido1)
                .build()
        );
    }

    @Test
    void createPago(){
        //Given
        //When
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        Pedido pedidoCreated=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2023-09-11"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(clienteCreated)
                .build()
        );
        Pago pagoCreated=pagoRepository.save(Pago.builder()
                .totalPago((float)20000)
                .fechaPago(LocalDate.parse("2023-09-10"))
                .metodoPago(PagoMetodo.fromString("PSE"))
                .pedido(pedidoCreated)
                .build()
        );
        //Then
        assertThat(pagoCreated).isNotNull();
        assertThat(pagoCreated.getPedido()).isEqualTo(pedidoCreated);
    }

    @Test
    void editPago(){
        //Given
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        Pedido pedidoCreated=pedidoRepository.save(Pedido.builder()
                .fecha(LocalDate.parse("2023-09-11"))
                .estado(PedidoStatus.fromString("ENTREGADO"))
                .cliente(clienteCreated)
                .build()
        );
        Pago pagoCreated=pagoRepository.save(Pago.builder()
                .totalPago((float)20000)
                .fechaPago(LocalDate.parse("2023-09-10"))
                .metodoPago(PagoMetodo.fromString("PSE"))
                .pedido(pedidoCreated)
                .build()
        );
        //When
        Pago pagoUpdated=pagoCreated;
        pagoUpdated.setFechaPago(LocalDate.parse("2023-09-11"));
        pagoUpdated=pagoRepository.save(pagoUpdated);
        //Then
        assertThat(pagoUpdated).isNotNull();
        assertThat(pagoUpdated.getPedido()).isEqualTo(pedidoCreated);
        assertThat(pagoUpdated.getFechaPago()).isEqualTo(pagoCreated.getFechaPago());
    }

    @Test
    void readPago(){

    }

    @Test
    void deletePago(){

    }

    @Test
    void findByFechaPagoBetween() {
        //Given
        //When
        List<Pago> pagoFound=pagoRepository.findByFechaPagoBetween(LocalDate.parse("2018-01-01"), LocalDate.parse("2021-01-01"));
        //Then
        assertThat(pagoFound).isNotNull();
        assertThat(pagoFound.get(0).getMetodoPago()).isEqualTo(pago1.getMetodoPago());
    }

    @Test
    void findByPedido_IdAndMetodoPago() {
        //Given
        //When
        List<Pago> pagoFound=pagoRepository.findByPedido_IdAndMetodoPago(pedido.getId(), PagoMetodo.fromString("NEQUI"));
        //Then
        assertThat(pagoFound).isNotNull();
        assertThat(pagoFound.get(0).getMetodoPago()).isEqualTo(pago.getMetodoPago());
    }
}