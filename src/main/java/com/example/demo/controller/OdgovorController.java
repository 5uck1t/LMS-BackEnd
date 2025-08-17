package com.example.demo.controller;

import com.example.demo.dto.OdgovorDTO;
import com.example.demo.dto.OdgovorDTO;
import com.example.demo.dto.PredmetDTO;
import com.example.demo.saveDto.OdgovorSaveDTO;
import com.example.demo.saveDto.OsobaSaveDTO;
import com.example.demo.service.OdgovorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/odgovors")
public class OdgovorController {

    @Autowired
    private OdgovorService odgovorService;

    @GetMapping
    public Iterable<OdgovorDTO> getAll() {
        return odgovorService.findAll();
    }

    @GetMapping("/active")
    public List<OdgovorDTO> getAllActive() {
        return odgovorService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<OdgovorDTO> getAllDeleted() {
        return odgovorService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdgovorDTO> getById(@PathVariable Long id) {
        Optional<OdgovorDTO> result = odgovorService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/zadatak/{zadatakId}")
    public List<OdgovorDTO> getOdgovoriByZadatakid(@PathVariable Long zadatakId) {
        return odgovorService.findByZadatakId(zadatakId);
    }

    @PostMapping
    public OdgovorDTO create(@RequestBody OdgovorSaveDTO odgovor) {
        return odgovorService.save(odgovor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        odgovorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        odgovorService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
