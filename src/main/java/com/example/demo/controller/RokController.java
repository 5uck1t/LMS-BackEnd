package com.example.demo.controller;

import com.example.demo.dto.RokDTO;
import com.example.demo.saveDto.RokSaveDTO;
import com.example.demo.service.RokService;
import com.example.demo.model.Rok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roks")
public class RokController {

    @Autowired
    private RokService rokService;

    @GetMapping
    public Iterable<RokDTO> getAll() {
        return rokService.findAll();
    }

    @GetMapping("/active")
    public List<RokDTO> getAllActive() {
        return rokService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<RokDTO> getAllDeleted() {
        return rokService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RokDTO> getById(@PathVariable Long id) {
        Optional<RokDTO> result = rokService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RokDTO create(@RequestBody RokSaveDTO rok) {
        return rokService.save(rok);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RokDTO> update(@PathVariable Long id, @RequestBody RokSaveDTO updatedRok) {
        Optional<RokDTO> optional = rokService.findById(id);
        if (optional.isPresent()) {
            RokSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedRok.getNaziv());
            existing.setPocetak(updatedRok.getPocetak());
            existing.setKraj(updatedRok.getKraj());
            existing.setObrisano(updatedRok.getObrisano());

            return ResponseEntity.ok(rokService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rokService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        rokService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
