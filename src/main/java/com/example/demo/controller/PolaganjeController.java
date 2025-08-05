package com.example.demo.controller;

import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.saveDto.OdgovorSaveDTO;
import com.example.demo.saveDto.PolaganjeSaveDTO;
import com.example.demo.service.PolaganjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polaganjes")
public class PolaganjeController {

    @Autowired
    private PolaganjeService polaganjeService;

    @GetMapping
    public Iterable<PolaganjeDTO> getAll() {
        return polaganjeService.findAll();
    }

    @GetMapping("/active")
    public List<PolaganjeDTO> getAllActive() {
        return polaganjeService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PolaganjeDTO> getAllDeleted() {
        return polaganjeService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolaganjeDTO> getById(@PathVariable Long id) {
        Optional<PolaganjeDTO> result = polaganjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PolaganjeDTO create(@RequestBody PolaganjeSaveDTO polaganje) {
        return polaganjeService.save(polaganje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        polaganjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        polaganjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
