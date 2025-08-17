package com.example.demo.controller;

import com.example.demo.dto.GodinaStudijaDTO;
import com.example.demo.saveDto.GodinaStudijaSaveDTO;
import com.example.demo.service.GodinaStudijaService;
import com.example.demo.model.GodinaStudija;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/godinastudijas")
public class GodinaStudijaController {

    @Autowired
    private GodinaStudijaService godinaStudijaService;

    @GetMapping
    public Iterable<GodinaStudijaDTO> getAll() {
        return godinaStudijaService.findAll();
    }

    @GetMapping("/active")
    public List<GodinaStudijaDTO> getAllActive() {
        return godinaStudijaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<GodinaStudijaDTO> getAllDeleted() {
        return godinaStudijaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> getById(@PathVariable Long id) {
        Optional<GodinaStudijaDTO> result = godinaStudijaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public GodinaStudijaDTO create(@RequestBody GodinaStudijaSaveDTO godinaStudija) {
        return godinaStudijaService.save(godinaStudija);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GodinaStudijaDTO> update(@PathVariable Long id, @RequestBody GodinaStudijaSaveDTO updatedGodinaStudija) {
        Optional<GodinaStudijaDTO> optional = godinaStudijaService.findById(id);
        if (optional.isPresent()) {
            GodinaStudijaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setGodina(updatedGodinaStudija.getGodina());
            existing.setStudijskiProgram_id(updatedGodinaStudija.getStudijskiProgram_id());
            existing.setObrisano(updatedGodinaStudija.getObrisano());

            return ResponseEntity.ok(godinaStudijaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        godinaStudijaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        godinaStudijaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
