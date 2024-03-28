package com.example.apifirst.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="detalles_envios")
public class DetalleEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String direccion;
    private String transportadora;
    private Integer numeroGuia;

    @OneToOne
    @JoinColumn(name = "FK_PEDIDO", referencedColumnName = "id")
    private Pedido pedido;
}
