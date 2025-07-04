package com.example.demo.controller;

import com.example.demo.dto.SilabusDTO;
import com.example.demo.saveDto.SilabusSaveDTO;
import com.example.demo.service.SilabusService;
import com.example.demo.model.Silabus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/silabuss")
public class SilabusController {

    @Autowired
    private SilabusService silabusService;

    @GetMapping
    public Iterable<SilabusDTO> getAll() {
        return silabusService.findAll();
    }

    @GetMapping("/active")
    public List<SilabusDTO> getAllActive() {
        return silabusService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<SilabusDTO> getAllDeleted() {
        return silabusService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SilabusDTO> getById(@PathVariable Long id) {
        Optional<SilabusDTO> result = silabusService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SilabusDTO create(@RequestBody SilabusSaveDTO silabus) {
        return silabusService.save(silabus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SilabusDTO> update(@PathVariable Long id, @RequestBody SilabusSaveDTO updatedSilabus) {
        Optional<SilabusDTO> optional = silabusService.findById(id);
        if (optional.isPresent()) {
            SilabusSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setOpis(updatedSilabus.getOpis());
            existing.setPredmet_id(updatedSilabus.getPredmet_id());
            existing.setObrisano(updatedSilabus.getObrisano());

            return ResponseEntity.ok(silabusService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        silabusService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        silabusService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
