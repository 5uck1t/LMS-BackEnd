package com.example.demo.controller;

import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.DrzavaDTO;
import com.example.demo.saveDto.DatumPredmetaSaveDTO;
import com.example.demo.service.DatumPredmetaService;
import com.example.demo.service.DrzavaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/datumipredmeta")
public class DatumPredmetaController {

    @Autowired
    private DatumPredmetaService datumPredmetaService;

    @GetMapping
    public Iterable<DatumPredmetaDTO> getAll() {
        return datumPredmetaService.findAll();
    }

    @GetMapping("/active")
    public List<DatumPredmetaDTO> getAllActive() {
        return datumPredmetaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<DatumPredmetaDTO> getAllDeleted() {
        return datumPredmetaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatumPredmetaDTO> getById(@PathVariable Long id) {
        Optional<DatumPredmetaDTO> result = datumPredmetaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DatumPredmetaDTO create(@RequestBody DatumPredmetaSaveDTO datumPredmeta) {
        return datumPredmetaService.save(datumPredmeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatumPredmetaDTO> update(@PathVariable Long id, @RequestBody DatumPredmetaDTO updatedDatumPredmeta) {
        Optional<DatumPredmetaDTO> optional = datumPredmetaService.findById(id);
        if (optional.isPresent()) {
            DatumPredmetaSaveDTO existing = optional.get().toSaveDto();
            // TODO: Manually copy fields from updatedDrzava to existing
            return ResponseEntity.ok(datumPredmetaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        datumPredmetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        datumPredmetaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
