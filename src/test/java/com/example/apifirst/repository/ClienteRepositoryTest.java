package com.example.apifirst.repository;

import com.example.apifirst.AbstractIntegrationDBTest;
import com.example.apifirst.entities.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ClienteRepositoryTest extends AbstractIntegrationDBTest {

    private ClienteRepository clienteRepository;
    Cliente cliente, cliente1;

    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        cliente=clienteRepository.save(Cliente.builder()
                .nombre("Juan")
                .email("Juandiaz@gmail.com")
                .direccion("La zona bananera")
                .build()
        );

        cliente1=clienteRepository.save(Cliente.builder()
                .nombre("Juancho")
                .email("Juanchodiaz@gmail.com")
                .direccion("La zona patatera")
                .build()
        );
    }

    @Test
    void givenNewCliente_whenClienteIsAdded_ThenClienteIsFound(){
        //Given a client
        //When its added
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        //Then
        assertThat(clienteCreated.getId()).isNotNull();
        assertThat(clienteCreated.getNombre()).matches("Jachan");
    }

    @Test
    void givenClienteData_WhenClienteIsModified_ThenChangesAreFound(){
        //Given a client
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        //When
        clienteCreated.setNombre("Hassan");
        clienteCreated.setDireccion("El rodadero");
        //Then
        assertThat(clienteCreated.getNombre()).matches("Hassan");
        assertThat(clienteCreated.getDireccion()).matches("El rodadero");
    }

    @Test
    void givenClienteId_WhenClienteIdMatches_ThenClienteIsFound(){
        //Given a client
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        //When
        Optional<Cliente> clienteFound=clienteRepository.findById(clienteCreated.getId());
        //Then
        assertThat(clienteFound).isPresent();
        assertThat(clienteFound.get().getNombre()).matches("Jachan");
    }

    @Test
    void givenClienteId_WhenClienteIsDeleted_ThenClienteIsNotFound(){
        //Given a client
        Cliente clienteCreated=clienteRepository.save(Cliente.builder()
                .nombre("Jachan")
                .email("BarraganJachan@gmail.com")
                .direccion("La playa")
                .build()
        );
        //When
        clienteRepository.deleteById(clienteCreated.getId());
        Optional<Cliente> clienteFound=clienteRepository.findById(clienteCreated.getId());
        //Then
        assertThat(clienteFound).isNotPresent();
    }

    @Test
    void givenEmail_whenEmailMatches_thenClienteIsFound() {
        //Given a direction
        //When
        List<Cliente> clienteFound=clienteRepository.findByEmail("Juandiaz@gmail.com");
        //Then
        assertThat(clienteFound.get(0).getId()).isNotNull();
        assertThat(cliente).isEqualTo(clienteFound.get(0));
    }

    @Test
    void givenDireccion_whenDireccionMatches_thenClienteIsFound() {
        //Given a direction
        //when
        List<Cliente> clienteFound=clienteRepository.findByDireccion("La zona patatera");
        //Then
        assertThat(clienteFound.get(0).getId()).isNotNull();
        assertThat(cliente1).isEqualTo(clienteFound.get(0));
    }

    @Test
    void givenStartOfName_whenStartMatches_thenClienteIsFound() {
        //Given the start of a name
        //When
        List<Cliente> clienteFound=clienteRepository.findByNombreStartingWith("Juan");
        //Then
        assertThat(clienteFound.get(0).getId()).isNotNull();
        assertThat(cliente).isEqualTo(clienteFound.get(0));
        assertThat(cliente1).isEqualTo(clienteFound.get(1));
    }
}