package com.example.apifirst.repository;

import com.example.apifirst.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByEmail(String email);
    List<Cliente> findByDireccion(String direccion);
    List<Cliente> findByNombreStartingWith(String nombre);
}
