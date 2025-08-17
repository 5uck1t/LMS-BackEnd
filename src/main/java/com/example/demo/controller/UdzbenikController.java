package com.example.demo.controller;

import com.example.demo.dto.UdzbenikDTO;
import com.example.demo.saveDto.UdzbenikSaveDTO;
import com.example.demo.service.UdzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/udzbeniks")
public class UdzbenikController {

    @Autowired
    private UdzbenikService udzbenikService;

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping
    public Iterable<UdzbenikDTO> getAll() {
        return udzbenikService.findAll();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/active")
    public List<UdzbenikDTO> getAllActive() {
        return udzbenikService.findAllActive();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/deleted")
    public List<UdzbenikDTO> getAllDeleted() {
        return udzbenikService.findAllDeleted();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/{id}")
    public ResponseEntity<UdzbenikDTO> getById(@PathVariable Long id) {
        Optional<UdzbenikDTO> result = udzbenikService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping
    public UdzbenikDTO create(@RequestBody UdzbenikSaveDTO dto) {
        return udzbenikService.save(dto);
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PutMapping("/{id}")
    public ResponseEntity<UdzbenikDTO> update(@PathVariable Long id, @RequestBody UdzbenikSaveDTO updated) {
        Optional<UdzbenikDTO> optional = udzbenikService.findById(id);
        if (optional.isPresent()) {
            UdzbenikSaveDTO existing = optional.get().toSaveDto();
            existing.setId(id);
            existing.setNaziv(updated.getNaziv());
            existing.setAutor(updated.getAutor());
            existing.setIsbn(updated.getIsbn());
            existing.setKolicina(updated.getKolicina());
            existing.setObrisano(updated.getObrisano());
            return ResponseEntity.ok(udzbenikService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        udzbenikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        udzbenikService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
