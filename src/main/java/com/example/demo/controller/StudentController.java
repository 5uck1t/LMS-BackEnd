package com.example.demo.controller;

import com.example.demo.dto.IstorijaStudiranjaDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.saveDto.StudentSaveDTO;
import com.example.demo.service.PohadjanjePredmetaService;
import com.example.demo.service.StudentService;
import com.example.demo.model.Osoba;
import com.example.demo.model.Student;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.repository.UlogovaniKorisnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;
    
    @Autowired
    private UlogovaniKorisnikRepository ulogovaniKorisnikRepository;

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
    
    
    @GetMapping("/{id}/istorija")
    public ResponseEntity<IstorijaStudiranjaDTO> getIstorija(@PathVariable Long id) {
    	return ResponseEntity.ok(pohadjanjePredmetaService.getIstorijaStudenta(id));

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
    
    @GetMapping("/idByUsername/{username}")
    public ResponseEntity<Long> getStudentIdByUsername(@PathVariable String username) {
        Optional<UlogovaniKorisnik> korisnikOpt = ulogovaniKorisnikRepository.findUlogovaniKorisnikByUsername(username);
        if (korisnikOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Osoba osoba = korisnikOpt.get().getOsoba();
        Optional<Student> studentOpt = studentService.findByOsoba(osoba);
        if (studentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentOpt.get().getId());
    }
}
