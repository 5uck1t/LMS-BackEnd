package com.example.demo.controller;

import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.saveDto.UlogovaniKorisnikSaveDTO;
import com.example.demo.service.UlogovaniKorisnikService;
import com.example.demo.model.UlogovaniKorisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ulogovanikorisniks")
public class UlogovaniKorisnikController {

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;

    @GetMapping
    public Iterable<UlogovaniKorisnikDTO> getAll() {
        return ulogovaniKorisnikService.findAll();
    }

    @GetMapping("/active")
    public List<UlogovaniKorisnikDTO> getAllActive() {
        return ulogovaniKorisnikService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<UlogovaniKorisnikDTO> getAllDeleted() {
        return ulogovaniKorisnikService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UlogovaniKorisnikDTO> getById(@PathVariable Long id) {
        Optional<UlogovaniKorisnikDTO> result = ulogovaniKorisnikService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UlogovaniKorisnikDTO create(@RequestBody UlogovaniKorisnikSaveDTO ulogovaniKorisnik) {
        return ulogovaniKorisnikService.save(ulogovaniKorisnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UlogovaniKorisnikDTO> update(@PathVariable Long id, @RequestBody UlogovaniKorisnikSaveDTO updatedUlogovaniKorisnik) {
        Optional<UlogovaniKorisnikDTO> optional = ulogovaniKorisnikService.findById(id);
        if (optional.isPresent()) {
            UlogovaniKorisnikSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setKorisnickoIme(updatedUlogovaniKorisnik.getKorisnickoIme());
            existing.setLozinka(updatedUlogovaniKorisnik.getLozinka());
            existing.setEmail(updatedUlogovaniKorisnik.getEmail());
            existing.setOsoba_id(updatedUlogovaniKorisnik.getOsoba_id());
            existing.setObrisano(updatedUlogovaniKorisnik.getObrisano());

            return ResponseEntity.ok(ulogovaniKorisnikService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ulogovaniKorisnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        ulogovaniKorisnikService.vrati(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/idByUsername/{username}")
    public ResponseEntity<Long> getUlogovaniKorisnikIdByUsername(@PathVariable String username) {
    	UlogovaniKorisnikDTO korisnikDto = ulogovaniKorisnikService.findByUsername(username);
    	if (korisnikDto == null) {
    	    return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(korisnikDto.getId());
    }


}
