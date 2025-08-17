package com.example.demo.controller;

import com.example.demo.dto.IzdavanjeDTO;
import com.example.demo.saveDto.IzdavanjeSaveDTO;
import com.example.demo.service.IzdavanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/izdavanjas")
public class IzdavanjeController {

    @Autowired
    private IzdavanjeService izdavanjeService;

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping
    public Iterable<IzdavanjeDTO> getAll() {
        return izdavanjeService.findAll();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/active")
    public List<IzdavanjeDTO> getAllActive() {
        return izdavanjeService.findAllActive();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/deleted")
    public List<IzdavanjeDTO> getAllDeleted() {
        return izdavanjeService.findAllDeleted();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @GetMapping("/{id}")
    public ResponseEntity<IzdavanjeDTO> getById(@PathVariable Long id) {
        Optional<IzdavanjeDTO> result = izdavanjeService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping
    public IzdavanjeDTO create(@RequestBody IzdavanjeSaveDTO dto) {
        return izdavanjeService.save(dto);
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PutMapping("/{id}")
    public ResponseEntity<IzdavanjeDTO> update(@PathVariable Long id, @RequestBody IzdavanjeSaveDTO updated) {
        Optional<IzdavanjeDTO> optional = izdavanjeService.findById(id);
        if (optional.isPresent()) {
            IzdavanjeSaveDTO existing = optional.get().toSaveDto();
            existing.setId(id);
            existing.setUdzbenik_id(updated.getUdzbenik_id());
            existing.setStudentNaGodini_id(updated.getStudentNaGodini_id());
            existing.setDatumIzdavanja(updated.getDatumIzdavanja());
            existing.setObrisano(updated.getObrisano());
            return ResponseEntity.ok(izdavanjeService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        izdavanjeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //@Secured({"ROLE_ADMIN","ROLE_SLUZBA"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        izdavanjeService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
