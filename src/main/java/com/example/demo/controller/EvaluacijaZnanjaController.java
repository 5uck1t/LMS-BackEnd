package com.example.demo.controller;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.saveDto.EvaluacijaZnanjaSaveDTO;
import com.example.demo.service.EvaluacijaZnanjaService;
import com.example.demo.model.EvaluacijaZnanja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/evaluacijaznanjas")
public class EvaluacijaZnanjaController {

    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;

    @GetMapping
    public Iterable<EvaluacijaZnanjaDTO> getAll() {
        return evaluacijaZnanjaService.findAll();
    }

    @GetMapping("/active")
    public List<EvaluacijaZnanjaDTO> getAllActive() {
        return evaluacijaZnanjaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<EvaluacijaZnanjaDTO> getAllDeleted() {
        return evaluacijaZnanjaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacijaZnanjaDTO> getById(@PathVariable Long id) {
        Optional<EvaluacijaZnanjaDTO> result = evaluacijaZnanjaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EvaluacijaZnanjaDTO create(@RequestBody EvaluacijaZnanjaSaveDTO evaluacijaZnanja) {
        return evaluacijaZnanjaService.save(evaluacijaZnanja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluacijaZnanjaDTO> update(@PathVariable Long id, @RequestBody EvaluacijaZnanjaSaveDTO updatedEvaluacijaZnanja) {
        Optional<EvaluacijaZnanjaDTO> optional = evaluacijaZnanjaService.findById(id);
        if (optional.isPresent()) {
            EvaluacijaZnanjaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedEvaluacijaZnanja.getNaziv());
            existing.setBrojBodova(updatedEvaluacijaZnanja.getBrojBodova());
            existing.setDatum(updatedEvaluacijaZnanja.getDatum());
            existing.setPohadjanjepredmeta_id(updatedEvaluacijaZnanja.getPohadjanjepredmeta_id());
            existing.setRok_id(updatedEvaluacijaZnanja.getRok_id());
            existing.setObrisano(updatedEvaluacijaZnanja.getObrisano());

            return ResponseEntity.ok(evaluacijaZnanjaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        evaluacijaZnanjaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        evaluacijaZnanjaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
