package com.example.apifirst.controller;

import com.example.apifirst.dto.DetalleEnvioDto;
import com.example.apifirst.dto.DetalleEnvioToSaveDto;
import com.example.apifirst.dto.ItemPedidoDto;
import com.example.apifirst.service.DetalleEnvioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shipping")
public class detalleEnvioController {
    private final DetalleEnvioService detalleEnvioService;

    public detalleEnvioController(DetalleEnvioService detalleEnvioService){
        this.detalleEnvioService=detalleEnvioService;
    }

    @GetMapping()
    public ResponseEntity<List<DetalleEnvioDto>> getAllDetallesEnvios(){
        List<DetalleEnvioDto> detalleEnvioFound=detalleEnvioService.getAllDetalleEnvio();
        return ResponseEntity.ok().body(detalleEnvioFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleEnvioDto> getDetalleEnvioById(@PathVariable("id") UUID id){
        DetalleEnvioDto detalleEnvioFound = detalleEnvioService.buscarDetalleEnvioById(id);
        return ResponseEntity.ok().body(detalleEnvioFound);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DetalleEnvioDto>> getDetalleEnvioByOrderId(@PathVariable("id") UUID id){
        List<DetalleEnvioDto> detalleEnvioFound = detalleEnvioService.findByPedido_Id(id);
        return ResponseEntity.ok().body(detalleEnvioFound);
    }

    @GetMapping("/carrier")
    public ResponseEntity<List<DetalleEnvioDto>> getDetalleEnvioByTransportadora(@PathVariable("name") String transportadora){
        List<DetalleEnvioDto> detalleEnvioFound = detalleEnvioService.findByTransportadora(transportadora);
        return ResponseEntity.ok().body(detalleEnvioFound);
    }

    @PostMapping()
    public ResponseEntity<DetalleEnvioDto> addDetalleEnvio(@RequestBody DetalleEnvioToSaveDto detalleEnvioToSaveDto){
        DetalleEnvioDto detalleEnvioSaved = detalleEnvioService.guardarDetalleEnvio(detalleEnvioToSaveDto);
        return ResponseEntity.ok().body(detalleEnvioSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity <DetalleEnvioDto> putDetalleEnvio(@PathVariable("id") UUID id, @RequestBody DetalleEnvioToSaveDto detalleEnvioToSaveDto){
        DetalleEnvioDto detalleEnvioPut=detalleEnvioService.actualizarDetalleEnvio(id, detalleEnvioToSaveDto);
        return ResponseEntity.ok().body(detalleEnvioPut);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteDetalleEnvio(@PathVariable("id") UUID id){
        detalleEnvioService.removerDetalleEnvio(id);
        return ResponseEntity.ok().body("Se ha eliminado correctamente");
    }
}
