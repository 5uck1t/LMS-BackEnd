package com.example.demo.controller;

import com.example.demo.dto.OsobaDTO;
import com.example.demo.saveDto.OsobaSaveDTO;
import com.example.demo.service.OsobaService;
import com.example.demo.model.Osoba;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/osobas")
public class OsobaController {

    @Autowired
    private OsobaService osobaService;

    @GetMapping
    public Iterable<OsobaDTO> getAll() {
        return osobaService.findAll();
    }

    @GetMapping("/active")
    public List<OsobaDTO> getAllActive() {
        return osobaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<OsobaDTO> getAllDeleted() {
        return osobaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OsobaDTO> getById(@PathVariable Long id) {
        Optional<OsobaDTO> result = osobaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OsobaDTO create(@RequestBody OsobaSaveDTO osoba) {
        return osobaService.save(osoba);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OsobaDTO> update(@PathVariable Long id, @RequestBody OsobaSaveDTO updatedOsoba) {
        Optional<OsobaDTO> optional = osobaService.findById(id);
        if (optional.isPresent()) {
            OsobaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setJmbg(updatedOsoba.getJmbg());
            existing.setIme(updatedOsoba.getIme());
            existing.setPrezime(updatedOsoba.getPrezime());
            existing.setAdresa_id(updatedOsoba.getAdresa_id());
            existing.setNastavnik_id(updatedOsoba.getNastavnik_id());
            existing.setStudent_id(updatedOsoba.getStudent_id());
            existing.setObrisano(updatedOsoba.getObrisano());

            return ResponseEntity.ok(osobaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        osobaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        osobaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
