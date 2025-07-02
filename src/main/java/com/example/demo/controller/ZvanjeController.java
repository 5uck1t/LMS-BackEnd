package com.example.demo.controller;

import com.example.demo.dto.ZvanjeDTO;
import com.example.demo.saveDto.ZvanjeSaveDTO;
import com.example.demo.service.ZvanjeService;
import com.example.demo.model.Zvanje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zvanjes")
public class ZvanjeController {

    @Autowired
    private ZvanjeService zvanjeService;

    @GetMapping
    public Iterable<ZvanjeDTO> getAll() {
        return zvanjeService.findAll();
    }

    @GetMapping("/active")
    public List<ZvanjeDTO> getAllActive() {
        return zvanjeService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ZvanjeDTO> getAllDeleted() {
        return zvanjeService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZvanjeDTO> getById(@PathVariable Long id) {
        Optional<ZvanjeDTO> result = zvanjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ZvanjeDTO create(@RequestBody ZvanjeSaveDTO zvanje) {
        return zvanjeService.save(zvanje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZvanjeDTO> update(@PathVariable Long id, @RequestBody ZvanjeSaveDTO updatedZvanje) {
        Optional<ZvanjeDTO> optional = zvanjeService.findById(id);
        if (optional.isPresent()) {
            ZvanjeSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setDatumIzbora(updatedZvanje.getDatumIzbora());
            existing.setDatumPrestanka(updatedZvanje.getDatumPrestanka());
            existing.setTipZvanja_id(updatedZvanje.getTipZvanja_id());
            existing.setNaucnaOblast_id(updatedZvanje.getNaucnaOblast_id());
            existing.setObrisano(updatedZvanje.getObrisano());

            return ResponseEntity.ok(zvanjeService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        zvanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        zvanjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
