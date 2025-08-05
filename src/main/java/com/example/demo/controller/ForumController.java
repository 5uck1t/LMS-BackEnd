package com.example.demo.controller;

import com.example.demo.dto.ForumDTO;
import com.example.demo.saveDto.ForumSaveDTO;
import com.example.demo.service.ForumService;
import com.example.demo.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping
    public Iterable<ForumDTO> getAll() {
        return forumService.findAll();
    }

    @GetMapping("/active")
    public List<ForumDTO> getAllActive() {
        return forumService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ForumDTO> getAllDeleted() {
        return forumService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForumDTO> getById(@PathVariable Long id) {
        Optional<ForumDTO> result = forumService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<ForumDTO> getByNaziv(@PathVariable String naziv) {
        Optional<ForumDTO> result = forumService.findByNaziv(naziv);
        System.out.println(result);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ForumDTO create(@RequestBody ForumSaveDTO forum) {
        return forumService.save(forum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForumDTO> update(@PathVariable Long id, @RequestBody ForumSaveDTO updatedForum) {
        Optional<ForumDTO> optional = forumService.findById(id);
        if (optional.isPresent()) {
            ForumSaveDTO existing = optional.get().toSaveDto();

            existing.setId(id);
            existing.setNaziv(updatedForum.getNaziv());
            existing.setForum_id(updatedForum.getForum_id());
            existing.setObrisano(updatedForum.getObrisano());

            return ResponseEntity.ok(forumService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        forumService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
