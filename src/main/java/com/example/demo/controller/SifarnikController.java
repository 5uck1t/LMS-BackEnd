package com.example.demo.controller;

import com.example.demo.dto.RokDTO;
import com.example.demo.model.Sifarnik;
import com.example.demo.saveDto.RokSaveDTO;
import com.example.demo.service.SifarnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sifarniks")
public class SifarnikController {

    @Autowired
    private SifarnikService sifarnikService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public Iterable<Sifarnik> getAll() {
        return sifarnikService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/active")
    public List<Sifarnik> getAllActive() {
        return sifarnikService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<Sifarnik> getAllDeleted() {
        return sifarnikService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sifarnik> getById(@PathVariable Long id) {
        Optional<Sifarnik> result = sifarnikService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sifarnik create(@RequestBody Sifarnik sifarnik) {
        return sifarnikService.save(sifarnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sifarnik> update(@PathVariable Long id, @RequestBody Sifarnik updatedSifarnik) {
        Optional<Sifarnik> optional = sifarnikService.findById(id);
        if (optional.isPresent()) {
            Sifarnik existing = optional.get();

            existing.setId(id);
            existing.setNaziv(updatedSifarnik.getNaziv());
            existing.setTekst(updatedSifarnik.getTekst());
            existing.setObrisano(updatedSifarnik.getObrisano());

            return ResponseEntity.ok(sifarnikService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sifarnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        sifarnikService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
