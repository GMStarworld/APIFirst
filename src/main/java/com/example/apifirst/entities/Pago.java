package com.example.apifirst.entities;

import com.example.apifirst.enumerations.PagoMetodo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Float totalPago;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPago;
    @Enumerated(EnumType.STRING)
    private PagoMetodo metodoPago;

    @OneToOne
    @JoinColumn(name = "FK_PEDIDO", referencedColumnName = "id")
    private Pedido pedido;
}
