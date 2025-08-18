package com.example.demo.controller;

import com.example.demo.dto.StranicaDTO;
import com.example.demo.saveDto.StranicaSaveDTO;
import com.example.demo.service.StranicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stranice")
public class StranicaController {

    @Autowired
    private StranicaService stranicaService;

    @GetMapping
    public Iterable<StranicaDTO> getAll() {
        return stranicaService.findAll();
    }

    @GetMapping("/active")
    public List<StranicaDTO> getAllActive() {
        return stranicaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<StranicaDTO> getAllDeleted() {
        return stranicaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StranicaDTO> getById(@PathVariable Long id) {
        Optional<StranicaDTO> result = stranicaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StranicaDTO create(@RequestBody StranicaSaveDTO stranica) {
        return stranicaService.save(stranica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StranicaDTO> update(@PathVariable Long id, @RequestBody StranicaSaveDTO updatedStranica) {
        Optional<StranicaDTO> optional = stranicaService.findById(id);
        if (optional.isPresent()) {
            StranicaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setBrojStranice(updatedStranica.getBrojStranice());
            existing.setSadrzaj(updatedStranica.getSadrzaj());
            existing.setUdzbenik_id(updatedStranica.getUdzbenik_id());
            existing.setObrisano(updatedStranica.getObrisano());

            return ResponseEntity.ok(stranicaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stranicaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        stranicaService.vrati(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byUdzbenik/{udzbenikId}")
    public List<StranicaDTO> getByUdzbenikId(@PathVariable Long udzbenikId) {
        return stranicaService.findByUdzbenikId(udzbenikId);
    }
}
