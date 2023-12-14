package com.example.proyecto01.application;

import com.example.proyecto01.domain.Compra;

import com.example.proyecto01.Service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> AllCompras(){
        List<Compra> compras = compraService.getAllCompra();
        return new ResponseEntity<>(compras,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> PostCompra(@RequestBody Compra compra){
        compraService.saveCompra(compra);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompra(@PathVariable Long id, @RequestBody Compra compra){
        Optional<Compra> updatedCompra = compraService.updateCompra(id,compra);
        return updatedCompra.isPresent() ? ResponseEntity.status(200).body("Updated") :
                ResponseEntity.status(404).body("Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchCompra(@PathVariable Long id, @RequestBody Compra compra){
        Optional<Compra> patchedCompra = compraService.patchCompra(id,compra);
        return patchedCompra.isPresent() ? ResponseEntity.status(200).body("Updated partially") :
                ResponseEntity.status(404).body("Not Found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable Long id) {
        Optional<Compra> deletedCompra = compraService.deleteCompra(id);
        return deletedCompra.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }

    @PostMapping("/añadir_compra/{id1}/{id2}")
    public ResponseEntity<String> Añadir_Compra_Automaticamente(@PathVariable Long id1, @PathVariable Long id2){
        compraService.Compra_Producto(id1,id2);
        return ResponseEntity.ok("Se añadio el producto automaticamente a la compra realizada.");
    }

}
