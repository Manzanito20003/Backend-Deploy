package com.example.proyecto01.application;

import com.example.proyecto01.domain.Compra;
import com.example.proyecto01.domain.Cliente;

import com.example.proyecto01.Service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> AllClientes(){
        List<Cliente> clientes = clienteService.getAllClient();
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> PostCliente(@RequestBody Cliente cliente){
        clienteService.saveCliente(cliente);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> updatedCliente = clienteService.updateCliente(id,cliente);
        return updatedCliente.isPresent() ? ResponseEntity.status(200).body("Updated") :
                ResponseEntity.status(404).body("Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> patchedCLiente = clienteService.patchCliente(id,cliente);
        return patchedCLiente.isPresent() ? ResponseEntity.status(200).body("Updated partially") :
                ResponseEntity.status(404).body("Not Found");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        Optional<Cliente> deletedCliente = clienteService.deleteCliente(id);
        return deletedCliente.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }

    @PostMapping("/realizar_compra/{id}/{id2}")
    public ResponseEntity<String> realizarCompra(@PathVariable Long id, @PathVariable Long id2) {
        clienteService.AgregarCompra(id, id2);
        return ResponseEntity.ok("Compra realizada con éxito");
    }

}
