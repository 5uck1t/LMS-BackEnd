package com.example.demo.controller;

import com.example.demo.dto.UniverzitetDTO;
import com.example.demo.saveDto.UniverzitetSaveDTO;
import com.example.demo.service.UniverzitetService;
import com.example.demo.model.Univerzitet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/univerzitets")
public class UniverzitetController {

    @Autowired
    private UniverzitetService univerzitetService;

    @GetMapping
    public Iterable<UniverzitetDTO> getAll() {
        return univerzitetService.findAll();
    }

    @GetMapping("/active")
    public List<UniverzitetDTO> getAllActive() {
        return univerzitetService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<UniverzitetDTO> getAllDeleted() {
        return univerzitetService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> getById(@PathVariable Long id) {
        Optional<UniverzitetDTO> result = univerzitetService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UniverzitetDTO create(@RequestBody UniverzitetSaveDTO univerzitet) {
        return univerzitetService.save(univerzitet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> update(@PathVariable Long id, @RequestBody UniverzitetSaveDTO updatedUniverzitet) {
        Optional<UniverzitetDTO> optional = univerzitetService.findById(id);
        if (optional.isPresent()) {
            UniverzitetSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setDatumOsnivanja(updatedUniverzitet.getDatumOsnivanja());
            existing.setAdresa_id(updatedUniverzitet.getAdresa_id());
            existing.setObrisano(updatedUniverzitet.getObrisano());

            return ResponseEntity.ok(univerzitetService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        univerzitetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        univerzitetService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
