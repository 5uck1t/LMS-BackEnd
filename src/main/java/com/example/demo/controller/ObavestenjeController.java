package com.example.demo.controller;

import com.example.demo.dto.ObavestenjeDTO;
import com.example.demo.saveDto.ObavestenjeSaveDTO;
import com.example.demo.service.ObavestenjeService;
import com.example.demo.model.Obavestenje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/obavestenjes")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    @GetMapping
    public Iterable<ObavestenjeDTO> getAll() {
        return obavestenjeService.findAll();
    }

    @GetMapping("/active")
    public List<ObavestenjeDTO> getAllActive() {
        return obavestenjeService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ObavestenjeDTO> getAllDeleted() {
        return obavestenjeService.findAllDeleted();
    }

    @GetMapping("/forum/{id}")
    public List<ObavestenjeDTO> getByForumId(@PathVariable Long id) {
        return obavestenjeService.findByForumId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> getById(@PathVariable Long id) {
        Optional<ObavestenjeDTO> result = obavestenjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ObavestenjeDTO create(@RequestBody ObavestenjeSaveDTO obavestenje) {
        return obavestenjeService.save(obavestenje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObavestenjeDTO> update(@PathVariable Long id, @RequestBody ObavestenjeSaveDTO updatedObavestenje) {
        Optional<ObavestenjeDTO> optional = obavestenjeService.findById(id);
        if (optional.isPresent()) {
            ObavestenjeSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaslov(updatedObavestenje.getNaslov());
            existing.setTekstObavjestenja(updatedObavestenje.getTekstObavjestenja());
            existing.setVremePostavljanja(updatedObavestenje.getVremePostavljanja());
            existing.setUlogovaniKorisnik_id(updatedObavestenje.getUlogovaniKorisnik_id());
            existing.setForum_id(updatedObavestenje.getForum_id());
            existing.setObrisano(updatedObavestenje.getObrisano());

            return ResponseEntity.ok(obavestenjeService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        obavestenjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        obavestenjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
