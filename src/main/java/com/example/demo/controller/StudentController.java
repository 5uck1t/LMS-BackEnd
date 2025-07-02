package com.example.demo.controller;

import com.example.demo.dto.StudentDTO;
import com.example.demo.saveDto.StudentSaveDTO;
import com.example.demo.service.StudentService;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Iterable<StudentDTO> getAll() {
        return studentService.findAll();
    }

    @GetMapping("/active")
    public List<StudentDTO> getAllActive() {
        return studentService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<StudentDTO> getAllDeleted() {
        return studentService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) {
        Optional<StudentDTO> result = studentService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentDTO create(@RequestBody StudentSaveDTO student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentSaveDTO updatedStudent) {
        Optional<StudentDTO> optional = studentService.findById(id);
        if (optional.isPresent()) {
            StudentSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setOsoba_id(updatedStudent.getOsoba_id());
            existing.setObrisano(updatedStudent.getObrisano());

            return ResponseEntity.ok(studentService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        studentService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
