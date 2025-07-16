package com.example.demo.controller;

import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.saveDto.StudijskiProgramSaveDTO;
import com.example.demo.saveDto.TerminSaveDTO;
import com.example.demo.service.TerminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/termins")
public class TerminController {

    @Autowired
    private TerminService terminService;

    @GetMapping
    public Iterable<TerminDTO> getAll() {
        return terminService.findAll();
    }

    @GetMapping("/active")
    public List<TerminDTO> getAllActive() {
        return terminService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<TerminDTO> getAllDeleted() {
        return terminService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TerminDTO> getById(@PathVariable Long id) {
        Optional<TerminDTO> result = terminService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TerminDTO create(@RequestBody TerminSaveDTO termin) {
        return terminService.save(termin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TerminDTO> update(@PathVariable Long id, @RequestBody TerminSaveDTO updatedTermin) {
        Optional<TerminDTO> optional = terminService.findById(id);
        if (optional.isPresent()) {
            TerminSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setDatum(updatedTermin.getDatum());
            existing.setVremePocetka(updatedTermin.getVremePocetka());
            existing.setVremeKraja(updatedTermin.getVremeKraja());
            existing.setRealizacijaPredmeta_id(updatedTermin.getRealizacijaPredmeta_id());
            existing.setObrisano(updatedTermin.getObrisano());

            return ResponseEntity.ok(terminService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        terminService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        terminService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
