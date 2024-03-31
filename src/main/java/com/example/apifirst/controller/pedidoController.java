package com.example.apifirst.controller;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;
import com.example.apifirst.dto.PedidoDto;
import com.example.apifirst.dto.PedidoToSaveDto;
import com.example.apifirst.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<PedidoDto> getPedidoByIdCliente(@PathVariable("customerId") UUID customerId){
        PedidoDto pedidoFound = pedidoService.buscarPedidoById(customerId);
        return ResponseEntity.ok().body(pedidoFound);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PedidoDto>> getPedidoByFechaBetween(@RequestParam("startDate")LocalDate fechaIni, @RequestParam("endDate")LocalDate fechaFin){
        List<PedidoDto> pedidoFound = pedidoService.findByFechaBetween(fechaIni, fechaFin);
        return ResponseEntity.ok().body(pedidoFound);
    }

    @PostMapping()
    public ResponseEntity<PedidoDto> addPedido(@RequestBody PedidoToSaveDto pedidoToSaveDto){
        PedidoDto pedidoSaved = pedidoService.guardarPedido(pedidoToSaveDto);
        return ResponseEntity.ok().body(pedidoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> putPedido(@PathVariable("id") UUID id, PedidoToSaveDto pedidoToSaveDto){
        PedidoDto pedidoFound = pedidoService.actualizarPedido(id, pedidoToSaveDto);
        return ResponseEntity.ok().body(pedidoFound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable("id") UUID id){
        pedidoService.removerPedido(id);
        return ResponseEntity.ok().body("Se ha borrado correctamente");
    }
}
