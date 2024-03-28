package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Cliente;
import com.example.apifirst.entities.DetalleEnvio;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.enumerations.PedidoStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DetalleEnvioRepositoryTest extends AbstractIntegrationDBTest {

    private DetalleEnvioRepository detalleEnvioRepository;
    private PedidoRepository pedidoRepository;
    private ClienteRepository clienteRepository;

    private DetalleEnvio detalle, detalle1;
    private Pedido pedido, pedido1;
    private Cliente cliente, cliente1;

    @Autowired
    public DetalleEnvioRepositoryTest(DetalleEnvioRepository detalleEnvioRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository){
        this.detalleEnvioRepository=detalleEnvioRepository;
        this.pedidoRepository=pedidoRepository;
        this.clienteRepository=clienteRepository;
    }

    @BeforeEach
    void setUp() {
        detalleEnvioRepository.deleteAll();

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

        detalle=detalleEnvioRepository.save(DetalleEnvio.builder()
                .numeroGuia(20)
                .direccion("Avenida de las bananeras")
                .transportadora("Rapidisimos SA")
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

        detalle1=detalleEnvioRepository.save(DetalleEnvio.builder()
                .numeroGuia(17)
                .direccion("Mi casa")
                .transportadora("La tortuga")
                .pedido(pedido1)
                .build()
        );
    }

    @Test
    void findByPedido_Id() {
        List<DetalleEnvio> detalleFound=detalleEnvioRepository.findByPedido_Id(pedido.getId());
        assertThat(detalleFound).isNotNull();
        assertThat(detalleFound.get(0).getPedido()).isEqualTo(pedido);
    }

    @Test
    void findByTransportadora() {
        List<DetalleEnvio> detalleFound=detalleEnvioRepository.findByTransportadora("La tortuga");
        assertThat(detalleFound).isNotNull();
        assertThat(detalleFound.get(0).getTransportadora()).matches("La tortuga");
    }

    @Test
    void findByPedido_Estado() {
        List<DetalleEnvio> detalleFound=detalleEnvioRepository.findByPedido_Estado(PedidoStatus.fromString("ENTREGADO"));
        assertThat(detalleFound).isNotNull();
        assertThat(detalleFound.get(0).getPedido().getEstado()).isEqualTo(pedido1.getEstado());
    }
}