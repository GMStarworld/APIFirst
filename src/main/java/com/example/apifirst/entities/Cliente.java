package com.example.apifirst.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private String email;
    private String direccion;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
}
