package com.example.demo.controller;

import com.example.demo.model.KancelarijskiMaterijal;
import com.example.demo.service.KancelarijskiMaterijalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/kancelarijski-materijal")
//public class KancelarijskiMaterijalController {
//
//    private final KancelarijskiMaterijalService service;
//
//    public KancelarijskiMaterijalController(KancelarijskiMaterijalService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<KancelarijskiMaterijal>> getAll() {
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    @PostMapping
//    public ResponseEntity<KancelarijskiMaterijal> create(@RequestBody KancelarijskiMaterijal materijal) {
//        KancelarijskiMaterijal sacuvan = service.save(materijal);
//        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable String id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/kancelarijski-materijal")
public class KancelarijskiMaterijalController {

    private final KancelarijskiMaterijalService service;

    public KancelarijskiMaterijalController(KancelarijskiMaterijalService service) {
        this.service = service;
    }

    // 1. Prikaz svih porudžbina (istorija)
    @GetMapping
    public ResponseEntity<List<KancelarijskiMaterijal>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // 2. Kreiranje nove porudžbine
    @PostMapping
    public ResponseEntity<KancelarijskiMaterijal> create(@RequestBody KancelarijskiMaterijal materijal) {
        KancelarijskiMaterijal sacuvan = service.save(materijal);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }

    // 3. Brisanje porudžbine
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 4. Izmena porudžbine
    @PutMapping("/{id}")
    public ResponseEntity<KancelarijskiMaterijal> update(@PathVariable String id,
                                                         @RequestBody KancelarijskiMaterijal materijal) {
        KancelarijskiMaterijal azuriran = service.update(id, materijal);
        return ResponseEntity.ok(azuriran);
    }

    // 5. Prikaz jedne porudžbine po ID (opciono)
    @GetMapping("/{id}")
    public ResponseEntity<KancelarijskiMaterijal> getById(@PathVariable String id) {
        return service.findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
