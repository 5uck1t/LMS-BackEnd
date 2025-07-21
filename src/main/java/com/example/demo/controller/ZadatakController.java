package com.example.demo.controller;

import com.example.demo.dto.OdgovorDTO;
import com.example.demo.dto.ZadatakDTO;
import com.example.demo.saveDto.ZadatakSaveDTO;
import com.example.demo.service.ZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zadataks")
public class ZadatakController {

    @Autowired
    private ZadatakService zadatakService;

    @GetMapping
    public Iterable<ZadatakDTO> getAll() {
        return zadatakService.findAll();
    }

    @GetMapping("/active")
    public List<ZadatakDTO> getAllActive() {
        return zadatakService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ZadatakDTO> getAllDeleted() {
        return zadatakService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZadatakDTO> getById(@PathVariable Long id) {
        Optional<ZadatakDTO> result = zadatakService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/evaluacija/{evaluacijaId}")
    public List<ZadatakDTO> getByEvaluacijaId(@PathVariable Long evaluacijaId) {
        return zadatakService.findByEvaluacijaId(evaluacijaId);
    }

    @PostMapping
    public ZadatakDTO create(@RequestBody ZadatakSaveDTO zadatak) {
        return zadatakService.save(zadatak);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        zadatakService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        zadatakService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
