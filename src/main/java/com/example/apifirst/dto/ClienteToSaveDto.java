package com.example.apifirst.dto;

import java.util.UUID;

public record ClienteToSaveDto(
        String nombre,
        String email,
        String direccion
){}
