package com.example.demo.controller;

import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.saveDto.StudentNaGodiniSaveDTO;
import com.example.demo.service.StudentNaGodiniService;
import com.example.demo.model.StudentNaGodini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/studentnagodinis")
public class StudentNaGodiniController {

    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    @GetMapping
    public Iterable<StudentNaGodiniDTO> getAll() {
        return studentNaGodiniService.findAll();
    }

    @GetMapping("/active")
    public List<StudentNaGodiniDTO> getAllActive() {
        return studentNaGodiniService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<StudentNaGodiniDTO> getAllDeleted() {
        return studentNaGodiniService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentNaGodiniDTO> getById(@PathVariable Long id) {
        Optional<StudentNaGodiniDTO> result = studentNaGodiniService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentNaGodiniDTO create(@RequestBody StudentNaGodiniSaveDTO studentNaGodini) {
        return studentNaGodiniService.save(studentNaGodini);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentNaGodiniDTO> update(@PathVariable Long id, @RequestBody StudentNaGodiniSaveDTO updatedStudentNaGodini) {
        Optional<StudentNaGodiniDTO> optional = studentNaGodiniService.findById(id);
        if (optional.isPresent()) {
            StudentNaGodiniSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setDatumUpisa(updatedStudentNaGodini.getDatumUpisa());
            existing.setBrojIndeksa(updatedStudentNaGodini.getBrojIndeksa());
            existing.setStudent_id(updatedStudentNaGodini.getStudent_id());
            existing.setGodinaStudija_id(updatedStudentNaGodini.getGodinaStudija_id());
            existing.setObrisano(updatedStudentNaGodini.getObrisano());

            return ResponseEntity.ok(studentNaGodiniService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentNaGodiniService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        studentNaGodiniService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
