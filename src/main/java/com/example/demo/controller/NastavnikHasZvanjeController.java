package com.example.demo.controller;

import com.example.demo.dto.NastavnikHasZvanjeDTO;
import com.example.demo.saveDto.NastavnikHasZvanjeSaveDTO;
import com.example.demo.service.NastavnikHasZvanjeService;
import com.example.demo.model.NastavnikHasZvanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/nastavnikhaszvanjes")
public class NastavnikHasZvanjeController {

    @Autowired
    private NastavnikHasZvanjeService nastavnikHasZvanjeService;

    @GetMapping
    public Iterable<NastavnikHasZvanjeDTO> getAll() {
        return nastavnikHasZvanjeService.findAll();
    }

    @GetMapping("/active")
    public List<NastavnikHasZvanjeDTO> getAllActive() {
        return nastavnikHasZvanjeService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<NastavnikHasZvanjeDTO> getAllDeleted() {
        return nastavnikHasZvanjeService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NastavnikHasZvanjeDTO> getById(@PathVariable Long id) {
        Optional<NastavnikHasZvanjeDTO> result = nastavnikHasZvanjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NastavnikHasZvanjeDTO create(@RequestBody NastavnikHasZvanjeSaveDTO nastavnikHasZvanje) {
        return nastavnikHasZvanjeService.save(nastavnikHasZvanje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NastavnikHasZvanjeDTO> update(@PathVariable Long id, @RequestBody NastavnikHasZvanjeSaveDTO updatedNastavnikHasZvanje) {
        Optional<NastavnikHasZvanjeDTO> optional = nastavnikHasZvanjeService.findById(id);
        if (optional.isPresent()) {
            NastavnikHasZvanjeSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNastavnik_id(updatedNastavnikHasZvanje.getNastavnik_id());
            existing.setZvanje_id(updatedNastavnikHasZvanje.getZvanje_id());

            return ResponseEntity.ok(nastavnikHasZvanjeService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nastavnikHasZvanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        nastavnikHasZvanjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
