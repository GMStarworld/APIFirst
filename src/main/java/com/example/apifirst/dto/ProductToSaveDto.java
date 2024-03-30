package com.example.apifirst.dto;

public record ProductToSaveDto(
        String nombre,
        Float precio,
        Long stock
) {}
