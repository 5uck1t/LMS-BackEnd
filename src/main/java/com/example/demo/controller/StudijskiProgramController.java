package com.example.demo.controller;

import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.saveDto.StudijskiProgramSaveDTO;
import com.example.demo.service.StudijskiProgramService;
import com.example.demo.model.StudijskiProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/studijskiprograms")
public class StudijskiProgramController {

    @Autowired
    private StudijskiProgramService studijskiProgramService;

    @GetMapping
    public Iterable<StudijskiProgramDTO> getAll() {
        return studijskiProgramService.findAll();
    }

    @GetMapping("/active")
    public List<StudijskiProgramDTO> getAllActive() {
        return studijskiProgramService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<StudijskiProgramDTO> getAllDeleted() {
        return studijskiProgramService.findAllDeleted();
    }

    @GetMapping("/katedra/{id}")
    public List<StudijskiProgramDTO> getByKatedraId(@PathVariable Long id) {
        return studijskiProgramService.findByKatedraId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> getById(@PathVariable Long id) {
        Optional<StudijskiProgramDTO> result = studijskiProgramService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudijskiProgramDTO create(@RequestBody StudijskiProgramSaveDTO studijskiProgram) {
        return studijskiProgramService.save(studijskiProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable Long id, @RequestBody StudijskiProgramSaveDTO updatedStudijskiProgram) {
        Optional<StudijskiProgramDTO> optional = studijskiProgramService.findById(id);
        if (optional.isPresent()) {
            StudijskiProgramSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedStudijskiProgram.getNaziv());
            existing.setKatedra_id(updatedStudijskiProgram.getKatedra_id());
            existing.setObrisano(updatedStudijskiProgram.getObrisano());

            return ResponseEntity.ok(studijskiProgramService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studijskiProgramService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        studijskiProgramService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
