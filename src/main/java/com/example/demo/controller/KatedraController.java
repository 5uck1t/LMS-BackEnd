package com.example.demo.controller;

import com.example.demo.dto.KatedraDTO;
import com.example.demo.saveDto.KatedraSaveDTO;
import com.example.demo.service.KatedraService;
import com.example.demo.model.Katedra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/katedras")
public class KatedraController {

    @Autowired
    private KatedraService katedraService;

    @GetMapping
    public Iterable<KatedraDTO> getAll() {
        return katedraService.findAll();
    }

    @GetMapping("/active")
    public List<KatedraDTO> getAllActive() {
        return katedraService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<KatedraDTO> getAllDeleted() {
        return katedraService.findAllDeleted();
    }

    @GetMapping("/fakultet/{id}")
    public List<KatedraDTO> getByFakultetId(@PathVariable Long id) {
        return katedraService.findByFakultetId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KatedraDTO> getById(@PathVariable Long id) {
        Optional<KatedraDTO> result = katedraService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public KatedraDTO create(@RequestBody KatedraSaveDTO katedra) {
        return katedraService.save(katedra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KatedraDTO> update(@PathVariable Long id, @RequestBody KatedraSaveDTO updatedKatedra) {
        Optional<KatedraDTO> optional = katedraService.findById(id);
        if (optional.isPresent()) {
            KatedraSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedKatedra.getNaziv());
            existing.setFakultet_id(updatedKatedra.getFakultet_id());
            existing.setSefKatedre_id(updatedKatedra.getSefKatedre_id());
            existing.setObrisano(updatedKatedra.getObrisano());

            return ResponseEntity.ok(katedraService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        katedraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        katedraService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
