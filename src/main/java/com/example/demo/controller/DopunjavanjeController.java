package com.example.demo.controller;

import com.example.demo.dto.DopunjavanjeDTO;
import com.example.demo.saveDto.DopunjavanjeSaveDTO;
import com.example.demo.service.DopunjavanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dopunjavanjas")
public class DopunjavanjeController {

    @Autowired
    private DopunjavanjeService dopunjavanjeService;

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping
    public Iterable<DopunjavanjeDTO> getAll() {
        return dopunjavanjeService.findAll();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/active")
    public List<DopunjavanjeDTO> getAllActive() {
        return dopunjavanjeService.findAllActive();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/deleted")
    public List<DopunjavanjeDTO> getAllDeleted() {
        return dopunjavanjeService.findAllDeleted();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/{id}")
    public ResponseEntity<DopunjavanjeDTO> getById(@PathVariable Long id) {
        Optional<DopunjavanjeDTO> result = dopunjavanjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping
    public DopunjavanjeDTO create(@RequestBody DopunjavanjeSaveDTO dto) {
        return dopunjavanjeService.save(dto);
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PutMapping("/{id}")
    public ResponseEntity<DopunjavanjeDTO> update(@PathVariable Long id, @RequestBody DopunjavanjeSaveDTO updated) {
        Optional<DopunjavanjeDTO> optional = dopunjavanjeService.findById(id);
        if (optional.isPresent()) {
            DopunjavanjeSaveDTO existing = optional.get().toSaveDto();
            existing.setId(id);
            existing.setUdzbenik_id(updated.getUdzbenik_id());
            existing.setDatum(updated.getDatum());
            existing.setKolicina(updated.getKolicina());
            existing.setNapomena(updated.getNapomena());
            existing.setObrisano(updated.getObrisano());
            return ResponseEntity.ok(dopunjavanjeService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dopunjavanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        dopunjavanjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
