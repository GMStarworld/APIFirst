package com.example.apifirst.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="detalles_envios")
public class DetalleEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String direccion;
    private String transportadora;
    private Integer numero_guia;

    @OneToOne
    @JoinColumn(name = "FK_PEDIDO", referencedColumnName = "id")
    private Pedido pedido;
}
