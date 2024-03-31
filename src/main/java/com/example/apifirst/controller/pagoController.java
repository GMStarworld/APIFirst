package com.example.apifirst.controller;

import com.example.apifirst.dto.PagoDto;
import com.example.apifirst.dto.PagoToSaveDto;
import com.example.apifirst.enumerations.PagoMetodo;
import com.example.apifirst.service.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class pagoController {
    private final PagoService pagoService;

    public pagoController (PagoService pagoService){
        this.pagoService=pagoService;
    }

    @GetMapping()
    public ResponseEntity<List<PagoDto>> getAllPagos(){
        List<PagoDto> pagoFound=pagoService.getAllPagos();
        return ResponseEntity.ok().body(pagoFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> getPagoById(@PathVariable("id")UUID id){
        PagoDto pagoFound=pagoService.buscarPagoById(id);
        return ResponseEntity.ok().body(pagoFound);
    }

    @GetMapping("/order/{orderId}/{method}")
    public ResponseEntity<List<PagoDto>> getPagoByOrderId(@PathVariable("orderId")UUID id, @PathVariable("method")String metodo){
        List<PagoDto> pagoFound=pagoService.findByPedido_IdAndMetodoPago(id, PagoMetodo.fromString(metodo));
        return ResponseEntity.ok().body(pagoFound);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<PagoDto>> getPagoByFechaBetween(@RequestParam("startDate") LocalDate fechaIni, @RequestParam("endDate")LocalDate fechaFin){
        List<PagoDto> pagoFound=pagoService.findByFechaPagoBetween(fechaIni, fechaFin);
        return ResponseEntity.ok().body(pagoFound);
    }

    @PostMapping()
    public ResponseEntity<PagoDto> addPago(@RequestBody PagoToSaveDto pagoToSaveDto){
        PagoDto pagoSaved = pagoService.guardarPago(pagoToSaveDto);
        return ResponseEntity.ok().body(pagoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> putPago(@PathVariable("id") UUID id, PagoToSaveDto pagoToSaveDto){
        PagoDto pagoFound = pagoService.actualizarPago(id, pagoToSaveDto);
        return ResponseEntity.ok().body(pagoFound);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePago(@PathVariable("id") UUID id){
        pagoService.removerPago(id);
        return ResponseEntity.ok().body("Se ha borrado correctamente");
    }
}
