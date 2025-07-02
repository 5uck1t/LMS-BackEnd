package com.example.demo.controller;

import com.example.demo.dto.NastavnikDTO;
import com.example.demo.saveDto.NastavnikSaveDTO;
import com.example.demo.service.NastavnikService;
import com.example.demo.model.Nastavnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nastavniks")
public class NastavnikController {

    @Autowired
    private NastavnikService nastavnikService;

    @GetMapping
    public Iterable<NastavnikDTO> getAll() {
        return nastavnikService.findAll();
    }

    @GetMapping("/active")
    public List<NastavnikDTO> getAllActive() {
        return nastavnikService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<NastavnikDTO> getAllDeleted() {
        return nastavnikService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikDTO> getById(@PathVariable Long id) {
        Optional<NastavnikDTO> result = nastavnikService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NastavnikDTO create(@RequestBody NastavnikSaveDTO nastavnik) {
        return nastavnikService.save(nastavnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikDTO> update(@PathVariable Long id, @RequestBody NastavnikSaveDTO updatedNastavnik) {
        Optional<NastavnikDTO> optional = nastavnikService.findById(id);
        if (optional.isPresent()) {
            NastavnikSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setBiografija(updatedNastavnik.getBiografija());
            existing.setOsoba_id(updatedNastavnik.getOsoba_id());
            existing.setFakultet_id(updatedNastavnik.getFakultet_id());
            existing.setObrisano(updatedNastavnik.getObrisano());

            return ResponseEntity.ok(nastavnikService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        nastavnikService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
