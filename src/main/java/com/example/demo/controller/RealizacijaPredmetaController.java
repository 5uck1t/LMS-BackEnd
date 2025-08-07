package com.example.demo.controller;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.saveDto.RealizacijaPredmetaSaveDTO;
import com.example.demo.service.RealizacijaPredmetaService;
import com.example.demo.model.RealizacijaPredmeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/realizacijapredmetas")
public class RealizacijaPredmetaController {

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @GetMapping
    public Iterable<RealizacijaPredmetaDTO> getAll() {
        return realizacijaPredmetaService.findAll();
    }

    @GetMapping("/active")
    public List<RealizacijaPredmetaDTO> getAllActive() {
        return realizacijaPredmetaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<RealizacijaPredmetaDTO> getAllDeleted() {
        return realizacijaPredmetaService.findAllDeleted();
    }

    @GetMapping("/nastavnik/{nastavnikId}")
    public List<PredmetDTO> getPredmetiByNastavnikId(@PathVariable Long nastavnikId) {
        return realizacijaPredmetaService.findPredmetiByNastavnikId(nastavnikId);
    }

    @GetMapping("/godinastudija/{godinastudijaid}")
    public List<RealizacijaPredmetaDTO> getRealizacijaPredmetaByGodinaStudijaId(@PathVariable Long godinastudijaid) {
        return realizacijaPredmetaService.findRealizacijaPredmetaByGodinaStudijaId(godinastudijaid);
    }

    @GetMapping("/studijskiProgram/{studijskiProgramId}")
    public List<RealizacijaPredmetaDTO> getRealizacijaPredmetaByStudijskiProgramId(@PathVariable Long studijskiProgramId) {
        return realizacijaPredmetaService.findRealizacijaPredmetaByStudijskiProgramId(studijskiProgramId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealizacijaPredmetaDTO> getById(@PathVariable Long id) {
        Optional<RealizacijaPredmetaDTO> result = realizacijaPredmetaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RealizacijaPredmetaDTO create(@RequestBody RealizacijaPredmetaSaveDTO realizacijaPredmeta) {
        return realizacijaPredmetaService.save(realizacijaPredmeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RealizacijaPredmetaDTO> update(@PathVariable Long id, @RequestBody RealizacijaPredmetaSaveDTO updatedRealizacijaPredmeta) {
        Optional<RealizacijaPredmetaDTO> optional = realizacijaPredmetaService.findById(id);
        if (optional.isPresent()) {
            RealizacijaPredmetaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setGodinaStudija_id(updatedRealizacijaPredmeta.getGodinaStudija_id());
            existing.setPredmet_id(updatedRealizacijaPredmeta.getPredmet_id());
            existing.setObrisano(updatedRealizacijaPredmeta.getObrisano());

            return ResponseEntity.ok(realizacijaPredmetaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        realizacijaPredmetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        realizacijaPredmetaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
