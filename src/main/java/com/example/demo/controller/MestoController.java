package com.example.demo.controller;

import com.example.demo.dto.MestoDTO;
import com.example.demo.saveDto.MestoSaveDTO;
import com.example.demo.service.MestoService;
import com.example.demo.model.Mesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mestos")
public class MestoController {

    @Autowired
    private MestoService mestoService;

    @GetMapping
    public Iterable<MestoDTO> getAll() {
        return mestoService.findAll();
    }

    @GetMapping("/active")
    public List<MestoDTO> getAllActive() {
        return mestoService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<MestoDTO> getAllDeleted() {
        return mestoService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MestoDTO> getById(@PathVariable Long id) {
        Optional<MestoDTO> result = mestoService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MestoDTO create(@RequestBody MestoSaveDTO mesto) {
        return mestoService.save(mesto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MestoDTO> update(@PathVariable Long id, @RequestBody MestoSaveDTO updatedMesto) {
        Optional<MestoDTO> optional = mestoService.findById(id);
        if (optional.isPresent()) {
            MestoSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedMesto.getNaziv());
            existing.setDrzava_id(updatedMesto.getDrzava_id());
            existing.setObrisano(updatedMesto.getObrisano());

            return ResponseEntity.ok(mestoService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mestoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        mestoService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
