package com.example.apifirst.entities;

import com.example.apifirst.enumerations.PagoMetodo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name ="pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Float total_pago;
    @Temporal(TemporalType.DATE)
    private LocalDate fecha_pago;
    @Enumerated(EnumType.STRING)
    private PagoMetodo metodo_pago;

    @OneToOne
    @JoinColumn(name = "FK_PEDIDO", referencedColumnName = "id")
    private Pedido pedido;
}
