package com.example.demo.controller;

import com.example.demo.dto.TipZvanjaDTO;
import com.example.demo.saveDto.TipZvanjaSaveDTO;
import com.example.demo.service.TipZvanjaService;
import com.example.demo.model.TipZvanja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipzvanjas")
public class TipZvanjaController {

    @Autowired
    private TipZvanjaService tipZvanjaService;

    @GetMapping
    public Iterable<TipZvanjaDTO> getAll() {
        return tipZvanjaService.findAll();
    }

    @GetMapping("/active")
    public List<TipZvanjaDTO> getAllActive() {
        return tipZvanjaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<TipZvanjaDTO> getAllDeleted() {
        return tipZvanjaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipZvanjaDTO> getById(@PathVariable Long id) {
        Optional<TipZvanjaDTO> result = tipZvanjaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipZvanjaDTO create(@RequestBody TipZvanjaSaveDTO tipZvanja) {
        return tipZvanjaService.save(tipZvanja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipZvanjaDTO> update(@PathVariable Long id, @RequestBody TipZvanjaSaveDTO updatedTipZvanja) {
        Optional<TipZvanjaDTO> optional = tipZvanjaService.findById(id);
        if (optional.isPresent()) {
            TipZvanjaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedTipZvanja.getNaziv());
            existing.setObrisano(updatedTipZvanja.getObrisano());

            return ResponseEntity.ok(tipZvanjaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipZvanjaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        tipZvanjaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
