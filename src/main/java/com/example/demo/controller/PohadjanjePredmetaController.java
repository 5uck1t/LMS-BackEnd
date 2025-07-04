package com.example.demo.controller;

import com.example.demo.dto.PohadjanjePredmetaDTO;
import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.model.Predmet;
import com.example.demo.saveDto.PohadjanjePredmetaSaveDTO;
import com.example.demo.service.PohadjanjePredmetaService;
import com.example.demo.model.PohadjanjePredmeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pohadjanjepredmetas")
public class PohadjanjePredmetaController {

    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @GetMapping
    public Iterable<PohadjanjePredmetaDTO> getAll() {
        return pohadjanjePredmetaService.findAll();
    }

    @GetMapping("/active")
    public List<PohadjanjePredmetaDTO> getAllActive() {
        return pohadjanjePredmetaService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<PohadjanjePredmetaDTO> getAllDeleted() {
        return pohadjanjePredmetaService.findAllDeleted();
    }

    @GetMapping("/studenti/{predmetId}")
    public ResponseEntity<List<StudentNaGodiniDTO>> getStudentiByPredmetId(@PathVariable Long predmetId) {
        List<StudentNaGodiniDTO> result = pohadjanjePredmetaService.findStudentiByPredmetId(predmetId);
        if(result.isEmpty()){
            return new ResponseEntity<List<StudentNaGodiniDTO>>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<StudentNaGodiniDTO>>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PohadjanjePredmetaDTO> getById(@PathVariable Long id) {
        Optional<PohadjanjePredmetaDTO> result = pohadjanjePredmetaService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/predmeti/{studentId}")
    public ResponseEntity<List<PredmetDTO>> getPredmetiByStudentId(@PathVariable Long studentId) {
        List<PredmetDTO> result = pohadjanjePredmetaService.findPredmetiByStudentId(studentId);
        if(result.isEmpty()){
            return new ResponseEntity<List<PredmetDTO>>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<PredmetDTO>>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/predmeti/polozeni/{studentId}")
    public ResponseEntity<List<PredmetDTO>> getPredmetiByStudentIdAndKonacnaOcenaNotNull(@PathVariable Long studentId){
        List<PredmetDTO> result = pohadjanjePredmetaService.findPredmetiByStudentIdAndKonacnaOcenaNotNull(studentId);

        if(result.isEmpty()){
            return new ResponseEntity<List<PredmetDTO>>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<PredmetDTO>>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/predmeti/nepolozeni/{studentId}")
    public ResponseEntity<List<PredmetDTO>> getPredmetiByStudentIdAndKonacnaOcenaisNull(@PathVariable Long studentId){
        List<PredmetDTO> result = pohadjanjePredmetaService.findPredmetiByStudentIdAndKonacnaOcenaIsNull(studentId);
        if(result.isEmpty()){
            return new ResponseEntity<List<PredmetDTO>>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<List<PredmetDTO>>(result, HttpStatus.OK);
        }
    }

    @PostMapping
    public PohadjanjePredmetaDTO create(@RequestBody PohadjanjePredmetaSaveDTO pohadjanjePredmeta) {
        return pohadjanjePredmetaService.save(pohadjanjePredmeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PohadjanjePredmetaDTO> update(@PathVariable Long id, @RequestBody PohadjanjePredmetaSaveDTO updatedPohadjanjePredmeta) {
        Optional<PohadjanjePredmetaDTO> optional = pohadjanjePredmetaService.findById(id);
        if (optional.isPresent()) {
            PohadjanjePredmetaSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setKonacnaOcena(updatedPohadjanjePredmeta.getKonacnaOcena());
            existing.setStudentNaGodini_id(updatedPohadjanjePredmeta.getStudentNaGodini_id());
            existing.setRealizacijaPredmeta_id(updatedPohadjanjePredmeta.getRealizacijaPredmeta_id());
            existing.setObrisano(updatedPohadjanjePredmeta.getObrisano());

            return ResponseEntity.ok(pohadjanjePredmetaService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pohadjanjePredmetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        pohadjanjePredmetaService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
