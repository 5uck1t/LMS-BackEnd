package com.example.demo.controller;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.dto.OsobaDTO;
import com.example.demo.dto.UniverzitetDTO;
import com.example.demo.model.Osoba;
import com.example.demo.model.Univerzitet;
import com.example.demo.saveDto.AdresaSaveDTO;
import com.example.demo.service.AdresaService;
import com.example.demo.model.Adresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/adresas")
public class AdresaController {

    @Autowired
    private AdresaService adresaService;

    @GetMapping
    public Iterable<AdresaDTO> getAll() {
        return adresaService.findAll();
    }

    @GetMapping("/active")
    public List<AdresaDTO> getAllActive() {
        return adresaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<AdresaDTO> getAllDeleted() {
        return adresaService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdresaDTO> getById(@PathVariable Long id) {
        Optional<AdresaDTO> result = adresaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AdresaDTO create(@RequestBody AdresaSaveDTO adresa) {
        return adresaService.save(adresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdresaDTO> update(@PathVariable Long id, @RequestBody AdresaSaveDTO updatedAdresa) {
        Optional<AdresaDTO> optional = adresaService.findById(id);
        if (optional.isPresent()) {
            AdresaSaveDTO existing = optional.get().toSaveDto();

            //TODO ovo treba dodati u ostale kontrolere
            existing.setBroj(updatedAdresa.getBroj());
            existing.setId(id);
            existing.setMesto_id(updatedAdresa.getMesto_id());
            existing.setObrisano(updatedAdresa.getObrisano());

            return ResponseEntity.ok(adresaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        adresaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
