package com.example.demo.controller;

import com.example.demo.dto.NaucnaOblastDTO;
import com.example.demo.saveDto.NaucnaOblastSaveDTO;
import com.example.demo.service.NaucnaOblastService;
import com.example.demo.model.NaucnaOblast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/naucnaoblasts")
public class NaucnaOblastController {

    @Autowired
    private NaucnaOblastService naucnaOblastService;

    @GetMapping
    public Iterable<NaucnaOblastDTO> getAll() {
        return naucnaOblastService.findAll();
    }

    @GetMapping("/active")
    public List<NaucnaOblastDTO> getAllActive() {
        return naucnaOblastService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<NaucnaOblastDTO> getAllDeleted() {
        return naucnaOblastService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NaucnaOblastDTO> getById(@PathVariable Long id) {
        Optional<NaucnaOblastDTO> result = naucnaOblastService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public NaucnaOblastDTO create(@RequestBody NaucnaOblastSaveDTO naucnaOblast) {
        return naucnaOblastService.save(naucnaOblast);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NaucnaOblastDTO> update(@PathVariable Long id, @RequestBody NaucnaOblastSaveDTO updatedNaucnaOblast) {
        Optional<NaucnaOblastDTO> optional = naucnaOblastService.findById(id);
        if (optional.isPresent()) {
            NaucnaOblastSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedNaucnaOblast.getNaziv());
            existing.setObrisano(updatedNaucnaOblast.getObrisano());

            return ResponseEntity.ok(naucnaOblastService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        naucnaOblastService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        naucnaOblastService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
