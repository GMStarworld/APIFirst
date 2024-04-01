package com.example.apifirst.controller;

import com.example.apifirst.dto.*;
import com.example.apifirst.entities.ItemPedido;
import com.example.apifirst.service.ItemPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order-items")
public class itemPedidoController {
    private final ItemPedidoService itemPedidoService;

    public itemPedidoController(ItemPedidoService itemPedidoService){
        this.itemPedidoService=itemPedidoService;
    }

    @GetMapping()
    public ResponseEntity<List<ItemPedidoDto>> getAllItemPedidos(){
        List<ItemPedidoDto> itemPedidoFound=itemPedidoService.getAllItems();
        return ResponseEntity.ok().body(itemPedidoFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoDto> getItemPedidoById(@PathVariable("id") UUID id){
        ItemPedidoDto itemPedidoFound = itemPedidoService.buscarItemPedidoById(id);
        return ResponseEntity.ok().body(itemPedidoFound);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<ItemPedidoDto>> getItemPedidoByIdPedido(@PathVariable("orderId") UUID id){
        List<ItemPedidoDto> itemPedidoFound = itemPedidoService.findByPedido_Id(id);
        return ResponseEntity.ok().body(itemPedidoFound);
    }

    @GetMapping("/order/{productId}")
    public ResponseEntity<List<ItemPedidoDto>> getItemPedidoByIdProduct(@PathVariable("productId") UUID id){
        List<ItemPedidoDto> itemPedidoFound = itemPedidoService.findByProducto_Id(id);
        return ResponseEntity.ok().body(itemPedidoFound);
    }

    @PostMapping()
    public ResponseEntity <ItemPedidoDto> addItemPedido(@RequestBody ItemPedidoToSaveDto itemPedidoToSaveDto){
        ItemPedidoDto itemPedidoSaved = itemPedidoService.guardarItemPedido(itemPedidoToSaveDto);
        return ResponseEntity.ok().body(itemPedidoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ItemPedidoDto> putCliente(@PathVariable("id")UUID id, @RequestBody ItemPedidoToSaveDto itemPedidoToSaveDto){
        ItemPedidoDto itemPedidoPut=itemPedidoService.actualizarItemPedido(id, itemPedidoToSaveDto);
        return ResponseEntity.ok().body(itemPedidoPut);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteItemPedido(@PathVariable("id") UUID id){
        itemPedidoService.removerItemPedido(id);
        return ResponseEntity.ok().body("Se ha eliminado correctamente");
    }
}
