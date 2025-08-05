package com.example.demo.controller;

import com.example.demo.dto.PravoPristupaDTO;
import com.example.demo.saveDto.PravoPristupaSaveDTO;
import com.example.demo.service.PravoPristupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pravopristupas")
public class PravoPristupaController {

    @Autowired
    private PravoPristupaService pravoPristupaService;

    @GetMapping
    public Iterable<PravoPristupaDTO> getAll() {
        return pravoPristupaService.findAll();
    }

    @GetMapping("/active")
    public List<PravoPristupaDTO> getAllActive() {
        return pravoPristupaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PravoPristupaDTO> getAllDeleted() {
        return pravoPristupaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PravoPristupaDTO> getById(@PathVariable Long id) {
        Optional<PravoPristupaDTO> result = pravoPristupaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PravoPristupaDTO create(@RequestBody PravoPristupaSaveDTO pravo) {
        return pravoPristupaService.save(pravo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pravoPristupaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        pravoPristupaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
