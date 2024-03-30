package com.example.apifirst.controller;

import com.example.apifirst.dto.ClienteDto;
import com.example.apifirst.dto.ClienteToSaveDto;
import com.example.apifirst.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class clienteController {
    private final ClienteService clienteService;

    public clienteController(ClienteService clienteService){
        this.clienteService=clienteService;
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDto>> getAllClients(){
        List<ClienteDto> clienteFound=clienteService.getAllClients();
        return ResponseEntity.ok().body(clienteFound);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable("id")UUID id){
        ClienteDto clienteFound = clienteService.buscarClienteById(id);
        return ResponseEntity.ok().body(clienteFound);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<ClienteDto>> getClienteByEmail(@PathVariable("email") String email){
        List<ClienteDto> clienteFound = clienteService.findByEmail(email);
        return ResponseEntity.ok().body(clienteFound);
    }

    @GetMapping("/city")
    public ResponseEntity<List<ClienteDto>> getClienteByDireccion(@RequestParam("cityName") String direccion){
        List<ClienteDto> clienteFound = clienteService.findByDireccion(direccion);
        return ResponseEntity.ok().body(clienteFound);
    }

    @PostMapping()
    public ResponseEntity <ClienteDto> addCliente(@RequestBody ClienteToSaveDto clienteToSaveDto){
        ClienteDto clienteSaved = clienteService.guardarCliente(clienteToSaveDto);
        return ResponseEntity.ok().body(clienteSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ClienteDto> putCliente(@PathVariable("id")UUID id, @RequestBody ClienteToSaveDto clienteToSaveDto){
        ClienteDto clientePut=clienteService.actualizarCliente(id, clienteToSaveDto);
        return ResponseEntity.ok().body(clientePut);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteCliente(@PathVariable("id") UUID id){
        clienteService.removerCliente(id);
        return ResponseEntity.ok().body("Se ha eliminado correctamente");
    }
}
