package com.example.proyecto01.application;

import com.example.proyecto01.Service.BebidaService;
import com.example.proyecto01.domain.Bebida;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/bebida")
public class BebidaController {
    @Autowired
    private BebidaService bebidaService;

    @GetMapping
    public ResponseEntity<List<Bebida>> AllBebida(){
        List<Bebida> bebida = bebidaService.getAllBebida();
        return new ResponseEntity<>(bebida,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> PostBebida(@RequestBody Bebida bebida){
        bebidaService.saveBebida(bebida);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Bebida bebida){
        Optional<Bebida> updatedBebida = bebidaService.updateBebida(id,bebida);
        return updatedBebida.isPresent() ? ResponseEntity.status(200).body("Updated") :
                ResponseEntity.status(404).body("Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchProducto(@PathVariable Long id, @RequestBody Bebida bebida){
        Optional<Bebida> patchBebida = bebidaService.patchBebida(id,bebida);
        return patchBebida.isPresent() ? ResponseEntity.status(200).body("Updated partially") :
                ResponseEntity.status(404).body("Not Found");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        Optional<Bebida> deletedBebida = bebidaService.deleteBebida(id);
        return deletedBebida.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }
}