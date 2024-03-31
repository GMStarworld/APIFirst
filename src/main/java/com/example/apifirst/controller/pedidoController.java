package com.example.apifirst.controller;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;
import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class pedidoController {
    private final PedidoService pedidoService;

    public pedidoController(PedidoService pedidoService){this.pedidoService=pedidoService;}

    @GetMapping()
    public ResponseEntity<List<PedidoDto>> getAllPedidos(){
        List<PedidoDto> pedidoFound=pedidoService.getAllPedidos();
        return ResponseEntity.ok().body(pedidoFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedidoById(@PathVariable("id") UUID id){
        PedidoDto pedidoFound = pedidoService.buscarPedidoById(id);
        return ResponseEntity.ok().body(pedidoFound);
    }

    @PostMapping()
    public ResponseEntity<PedidoDto> addPedido(@RequestBody PedidoToSaveDto pedidoToSaveDto){
        PedidoDto pedidoSaved = pedidoService.guardarPedido(pedidoToSaveDto);
        return ResponseEntity.ok().body(pedidoSaved);
    }
}
