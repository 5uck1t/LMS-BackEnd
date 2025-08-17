package com.example.demo.controller;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.saveDto.PredmetSaveDTO;
import com.example.demo.service.PredmetService;
import com.example.demo.model.Predmet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/predmets")
public class PredmetController {

    @Autowired
    private PredmetService predmetService;

    @GetMapping
    public Iterable<PredmetDTO> getAll() {
        return predmetService.findAll();
    }

    @GetMapping("/active")
    public List<PredmetDTO> getAllActive() {
        return predmetService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PredmetDTO> getAllDeleted() {
        return predmetService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PredmetDTO> getById(@PathVariable Long id) {
        Optional<PredmetDTO> result = predmetService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PredmetDTO create(@RequestBody PredmetSaveDTO predmet) {
        return predmetService.save(predmet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PredmetDTO> update(@PathVariable Long id, @RequestBody PredmetSaveDTO updatedPredmet) {
        Optional<PredmetDTO> optional = predmetService.findById(id);
        if (optional.isPresent()) {
            PredmetSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedPredmet.getNaziv());
            existing.setEspb(updatedPredmet.getEspb());
            existing.setObavezan(updatedPredmet.getObavezan());
            existing.setBrojPredavanja(updatedPredmet.getBrojPredavanja());
            existing.setBrojVezbi(updatedPredmet.getBrojVezbi());
            existing.setDrugiObliciNastave(updatedPredmet.getDrugiObliciNastave());
            existing.setIstrazivackiRad(updatedPredmet.getIstrazivackiRad());
            existing.setOstaliCasovi(updatedPredmet.getOstaliCasovi());
            existing.setBrojSemestara(updatedPredmet.getBrojSemestara());
            existing.setObrisano(updatedPredmet.getObrisano());

            return ResponseEntity.ok(predmetService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        predmetService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        predmetService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
