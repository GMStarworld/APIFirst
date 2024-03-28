package com.example.apifirst.enumerations;

public enum PedidoStatus {
    PENDIENTE, ENVIADO, ENTREGADO;

    public static PedidoStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "PENDIENTE" -> PENDIENTE;
            case "ENVIADO" -> ENVIADO;
            case "ENTREGADO" -> ENTREGADO;
            default -> throw new IllegalArgumentException("Estado de pedido inválido: " + value);
        };
    }
}