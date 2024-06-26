package com.example.apifirst.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name ="productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nombre;
    private Float precio;
    private Long stock;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ItemPedido> item_pedido;
}
