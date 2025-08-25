package com.example.demo.controller;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.ForumHasKorisnikDTO;
import com.example.demo.saveDto.ForumHasKorisnikSaveDTO;
import com.example.demo.service.ForumHasKorisnikService;
import com.example.demo.model.ForumHasKorisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/forumhaskorisniks")
public class ForumHasKorisnikController {

    @Autowired
    private ForumHasKorisnikService forumHasKorisnikService;

    @GetMapping
    public Iterable<ForumHasKorisnikDTO> getAll() {
        return forumHasKorisnikService.findAll();
    }

    @GetMapping("/active")
    public List<ForumHasKorisnikDTO> getAllActive() {
        return forumHasKorisnikService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ForumHasKorisnikDTO> getAllDeleted() {
        return forumHasKorisnikService.findAllDeleted();
    }

    @GetMapping("/korisnik/{id}")
    public List<ForumDTO> getByKorisnikId(@PathVariable Long id) {
        return forumHasKorisnikService.getForumsByUserId(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ForumHasKorisnikDTO> getById(@PathVariable Long id) {
        Optional<ForumHasKorisnikDTO> result = forumHasKorisnikService.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK","ROLE_STUDENT"})
    @PostMapping
    public ForumHasKorisnikDTO create(@RequestBody ForumHasKorisnikSaveDTO forumHasKorisnik) {
        return forumHasKorisnikService.save(forumHasKorisnik);
    }

    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    @PutMapping("/{id}")
    public ResponseEntity<ForumHasKorisnikDTO> update(@PathVariable Long id, @RequestBody ForumHasKorisnikSaveDTO updatedForumHasKorisnik) {
        Optional<ForumHasKorisnikDTO> optional = forumHasKorisnikService.findById(id);
        if (optional.isPresent()) {
            ForumHasKorisnikSaveDTO existing = optional.get().toSaveDto();
            // TODO: Manually copy fields from updatedForumHasKorisnik to existing
            return ResponseEntity.ok(forumHasKorisnikService.save(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        forumHasKorisnikService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Secured({"ROLE_ADMIN","ROLE_NASTAVNIK"})
    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        forumHasKorisnikService.vrati(id);
        return ResponseEntity.noContent().build();
    }
}
