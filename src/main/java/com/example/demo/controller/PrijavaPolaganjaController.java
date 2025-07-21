package com.example.demo.controller;

import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.saveDto.PolaganjeSaveDTO;
import com.example.demo.saveDto.PrijavaPolaganjaSaveDTO;
import com.example.demo.service.PrijavaPolaganjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prijavapolaganjas")
public class PrijavaPolaganjaController {

    @Autowired
    private PrijavaPolaganjaService prijavaPolaganjaService;

    @GetMapping
    public Iterable<PrijavaPolaganjaDTO> getAll() {
        return prijavaPolaganjaService.findAll();
    }

    @GetMapping("/active")
    public List<PrijavaPolaganjaDTO> getAllActive() {
        return prijavaPolaganjaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PrijavaPolaganjaDTO> getAllDeleted() {
        return prijavaPolaganjaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrijavaPolaganjaDTO> getById(@PathVariable Long id) {
        Optional<PrijavaPolaganjaDTO> result = prijavaPolaganjaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pohadjanje/{pohadjanjeId}")
    public List<PrijavaPolaganjaDTO> getByPohadjanjeId(@PathVariable Long pohadjanjeId) {
        return prijavaPolaganjaService.findByPohadjanjeId(pohadjanjeId);
    }

    @GetMapping("/polaganje/{polaganjeId}")
    public List<PrijavaPolaganjaDTO> getByPolaganjeId(@PathVariable Long polaganjeId) {
        return prijavaPolaganjaService.findByPolaganjeId(polaganjeId);
    }

    @PostMapping
    public PrijavaPolaganjaDTO create(@RequestBody PrijavaPolaganjaSaveDTO prijavaPolaganja) {
        return prijavaPolaganjaService.save(prijavaPolaganja);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prijavaPolaganjaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        prijavaPolaganjaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
