package com.example.proyecto01.application;
import com.example.proyecto01.Service.CafeService;
import com.example.proyecto01.Service.SnackService;
import com.example.proyecto01.domain.Cafe;
import com.example.proyecto01.domain.Snack;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:19006")

@RequestMapping("/snack")
public class SnackController {

    @Autowired
    private SnackService snackService;

    @GetMapping
    public ResponseEntity<List<Snack>> AllSnack(){
        List<Snack> cafe = snackService.getAllSnack();
        return new ResponseEntity<>(cafe,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> PostSnack(@RequestBody Snack snack){
        snackService.saveSnack(snack);
        return ResponseEntity.status(201).body("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSnack(@PathVariable Long id, @RequestBody Snack snack){
        Optional<Snack> updatedSnack = snackService.updateSnack(id,snack);
        return updatedSnack.isPresent() ? ResponseEntity.status(200).body("Updated") :
                ResponseEntity.status(404).body("Not Found");

    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchSnack(@PathVariable Long id, @RequestBody Snack snack){
        Optional<Snack> patchSnack = snackService.patchSnack(id,snack);
        return patchSnack.isPresent() ? ResponseEntity.status(200).body("Updated partially") :
                ResponseEntity.status(404).body("Not Found");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSnack(@PathVariable Long id) {
        Optional<Snack> deletedSnack = snackService.deleteSnack(id);
        return deletedSnack.isPresent() ? ResponseEntity.status(200).body("Deleted") : ResponseEntity.status(404).body("Not Found");
    }
}
