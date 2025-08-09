package com.example.demo.controller;

import com.example.demo.dto.DrzavaDTO;
import com.example.demo.saveDto.DrzavaSaveDTO;
import com.example.demo.service.DrzavaService;
import com.example.demo.model.Drzava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/drzavas")
public class DrzavaController {

    @Autowired
    private DrzavaService drzavaService;

    @GetMapping
    public Iterable<DrzavaDTO> getAll() {
        return drzavaService.findAll();
    }

    @GetMapping("/active")
    public List<DrzavaDTO> getAllActive() {
        return drzavaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<DrzavaDTO> getAllDeleted() {
        return drzavaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrzavaDTO> getById(@PathVariable Long id) {
        Optional<DrzavaDTO> result = drzavaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK"})
    @PostMapping
    public DrzavaDTO create(@RequestBody DrzavaSaveDTO drzava) {
        return drzavaService.save(drzava);
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK"})
    @PutMapping("/{id}")
    public ResponseEntity<DrzavaDTO> update(@PathVariable Long id, @RequestBody DrzavaSaveDTO updatedDrzava) {
        Optional<DrzavaDTO> optional = drzavaService.findById(id);
        if (optional.isPresent()) {
            DrzavaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedDrzava.getNaziv());
            existing.setObrisano(updatedDrzava.getObrisano());

            return ResponseEntity.ok(drzavaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        drzavaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        drzavaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
