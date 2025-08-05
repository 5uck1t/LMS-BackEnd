package com.example.demo.controller;

import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.model.Polaganje;
import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.saveDto.PolaganjeSaveDTO;
import com.example.demo.saveDto.PrijavaPolaganjaSaveDTO;
import com.example.demo.service.PolaganjeService;
import com.example.demo.service.PrijavaPolaganjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prijavapolaganjas")
public class PrijavaPolaganjaController {

    @Autowired
    private PrijavaPolaganjaService prijavaPolaganjaService;
    
    @Autowired
    private PolaganjeService polaganjeService;

    @GetMapping
    public Iterable<PrijavaPolaganjaDTO> getAll() {
        return prijavaPolaganjaService.findAll();
    }

    @GetMapping("/active")
    public List<PrijavaPolaganjaDTO> getAllActive() {
        return prijavaPolaganjaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PrijavaPolaganjaDTO> getAllDeleted() {
        return prijavaPolaganjaService.findAllDeleted();
    }
    
    
    @PutMapping("/ocena/{id}")
    public ResponseEntity<PrijavaPolaganjaDTO> update(@PathVariable Long id,
                                                      @RequestBody PrijavaPolaganjaSaveDTO updated) {
        Optional<PrijavaPolaganjaDTO> optional = prijavaPolaganjaService.findById(id);
        if (optional.isPresent()) {
            PrijavaPolaganjaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id); // već setovano ali radi jasnoće
            existing.setBrojBodova(updated.getBrojBodova());
            existing.setObrisano(updated.getObrisano());

            return ResponseEntity.ok(prijavaPolaganjaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    
    @PostMapping("/student/{studentId}/prijava/{polaganjeId}")
    public ResponseEntity<?> prijaviIspit(@PathVariable Long studentId, @PathVariable Long polaganjeId) {
        try {
            prijavaPolaganjaService.prijaviIspit(studentId, polaganjeId);
            return ResponseEntity.ok("Uspesno prijavljen ispit.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<PrijavaPolaganjaDTO>> findAllForStudentWithTimeLimit(@PathVariable Long studentId) {
        List<PrijavaPolaganjaDTO> prijave = prijavaPolaganjaService.findAllForStudentWithin15Days(studentId);
        return ResponseEntity.ok(prijave);
    }


    
    @GetMapping("/dostupna/{studentId}")
    public ResponseEntity<List<PolaganjeDTO>> getDostupnaPolaganja(@PathVariable Long studentId) {
        List<PolaganjeDTO> dostupna = prijavaPolaganjaService.findDostupnaPolaganja(studentId);
        return ResponseEntity.ok(dostupna);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PrijavaPolaganjaDTO> getById(@PathVariable Long id) {
        Optional<PrijavaPolaganjaDTO> result = prijavaPolaganjaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pohadjanje/{pohadjanjeId}")
    public List<PrijavaPolaganjaDTO> getByPohadjanjeId(@PathVariable Long pohadjanjeId) {
        return prijavaPolaganjaService.findByPohadjanjeId(pohadjanjeId);
    }

    @GetMapping("/polaganje/{polaganjeId}")
    public List<PrijavaPolaganjaDTO> getByPolaganjeId(@PathVariable Long polaganjeId) {
        return prijavaPolaganjaService.findByPolaganjeId(polaganjeId);
    }

    @PostMapping
    public PrijavaPolaganjaDTO create(@RequestBody PrijavaPolaganjaSaveDTO prijavaPolaganja) {
        return prijavaPolaganjaService.save(prijavaPolaganja);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prijavaPolaganjaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        prijavaPolaganjaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
