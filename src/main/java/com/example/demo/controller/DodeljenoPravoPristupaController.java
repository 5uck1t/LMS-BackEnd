package com.example.demo.controller;

import com.example.demo.dto.DodeljenoPravoPristupaDTO;
import com.example.demo.saveDto.DodeljenoPravoPristupaSaveDTO;
import com.example.demo.service.DodeljenoPravoPristupaService;
import com.example.demo.model.DodeljenoPravoPristupa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dodeljenopravopristupas")
public class DodeljenoPravoPristupaController {

    @Autowired
    private DodeljenoPravoPristupaService dodeljenoPravoPristupaService;

    @GetMapping
    public Iterable<DodeljenoPravoPristupaDTO> getAll() {
        return dodeljenoPravoPristupaService.findAll();
    }

    @GetMapping("/active")
    public List<DodeljenoPravoPristupaDTO> getAllActive() {
        return dodeljenoPravoPristupaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<DodeljenoPravoPristupaDTO> getAllDeleted() {
        return dodeljenoPravoPristupaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DodeljenoPravoPristupaDTO> getById(@PathVariable Long id) {
        Optional<DodeljenoPravoPristupaDTO> result = dodeljenoPravoPristupaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DodeljenoPravoPristupaDTO create(@RequestBody DodeljenoPravoPristupaSaveDTO dodeljenoPravoPristupa) {
        return dodeljenoPravoPristupaService.save(dodeljenoPravoPristupa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DodeljenoPravoPristupaDTO> update(@PathVariable Long id, @RequestBody DodeljenoPravoPristupaDTO updatedDodeljenoPravoPristupa) {
        Optional<DodeljenoPravoPristupaDTO> optional = dodeljenoPravoPristupaService.findById(id);
        if (optional.isPresent()) {
            DodeljenoPravoPristupaSaveDTO existing = optional.get().toSaveDto();
            // TODO: Manually copy fields from updatedDodeljenoPravoPristupa to existing
            return ResponseEntity.ok(dodeljenoPravoPristupaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dodeljenoPravoPristupaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        dodeljenoPravoPristupaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
