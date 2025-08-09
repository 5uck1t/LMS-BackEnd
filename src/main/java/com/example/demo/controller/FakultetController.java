package com.example.demo.controller;

import com.example.demo.dto.FakultetDTO;
import com.example.demo.saveDto.FakultetSaveDTO;
import com.example.demo.service.FakultetService;
import com.example.demo.model.Fakultet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/fakultets")
public class FakultetController {

    @Autowired
    private FakultetService fakultetService;

    @GetMapping
    public Iterable<FakultetDTO> getAll() {
        return fakultetService.findAll();
    }

    @GetMapping("/active")
    public List<FakultetDTO> getAllActive() {
        return fakultetService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<FakultetDTO> getAllDeleted() {
        return fakultetService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FakultetDTO> getById(@PathVariable Long id) {
        Optional<FakultetDTO> result = fakultetService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping
    public FakultetDTO create(@RequestBody FakultetSaveDTO fakultet) {
        return fakultetService.save(fakultet);
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PutMapping("/{id}")
    public ResponseEntity<FakultetDTO> update(@PathVariable Long id, @RequestBody FakultetSaveDTO updatedFakultet) {
        Optional<FakultetDTO> optional = fakultetService.findById(id);
        if (optional.isPresent()) {
            FakultetSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedFakultet.getNaziv());
            existing.setUniverzitet_id(updatedFakultet.getUniverzitet_id());
            existing.setDekan_id(updatedFakultet.getDekan_id());
            existing.setObrisano(updatedFakultet.getObrisano());

            return ResponseEntity.ok(fakultetService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fakultetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        fakultetService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
