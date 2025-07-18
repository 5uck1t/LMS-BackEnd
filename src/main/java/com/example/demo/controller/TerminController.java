package com.example.demo.controller;

import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.model.Termin;
import com.example.demo.repository.TerminRepository;
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
    
    @Autowired
    private TerminRepository terminRepository;

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
    public ResponseEntity<TerminDTO> update(@PathVariable Long id, @RequestBody TerminDTO dto) {
        Optional<Termin> optional = terminRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Termin termin = optional.get();
        
        termin.setDatum(dto.getDatum());
        termin.setVremePocetka(dto.getVremePocetka());
        termin.setVremeKraja(dto.getVremeKraja());
        termin.setIshod(dto.getIshod());
        
        terminRepository.save(termin);
        return ResponseEntity.ok(termin.toDto());
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
    
    @GetMapping("/predmet/{id}")
    public List<TerminDTO> getByRealizacijaPredmeta(@PathVariable Long id) {
        return terminService.findByRealizacijaPredmeta(id);
    }

}
