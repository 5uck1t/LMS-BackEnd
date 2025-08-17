//package com.example.demo.controller;
//
//
//
//import org.apache.jena.rdfconnection.RDFConnection;
//import org.apache.jena.rdfconnection.RDFConnectionFuseki;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.demo.model.KancelarijskiMaterijal;
//import com.example.demo.model.Trebovanje;
//import com.example.demo.service.KancelarijskiMaterijalService;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@RestController
////@CrossOrigin(origins = "http://localhost:4200")
////@RequestMapping("/kancelarijski-materijal")
////public class TrebovanjeController {
////
////    private static final String FUSEKI_URL = "http://localhost:3030/trebovanje";
////
////    @GetMapping
////    public List<Trebovanje> getAll() {
////        List<Trebovanje> results = new ArrayList<>();
////
////        String queryStr = "PREFIX t: <http://www.example.com/trebovanje#> " +
////                          "SELECT ?id ?datum ?kolicina ?rob WHERE { " +
////                          "?id a t:Trebovanje; t:datum ?datum; t:kolicina ?kolicina; t:rob ?rob }";
////
////        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
////            conn.querySelect(queryStr, qs -> {
////                String id = qs.getResource("id").getLocalName();
////                String datum = qs.getLiteral("datum").getString();
////                String kolicina = qs.getLiteral("kolicina").getString();
////                String rob = qs.getLiteral("rob").getString();
////                results.add(new Trebovanje(id, datum, kolicina, rob));
////            });
////        }
////
////        return results;
////    }
////
////    @PostMapping
////    public String create(@RequestBody Trebovanje t) {
////        String insert = String.format(
////                "PREFIX t: <http://www.example.com/trebovanje#> " +
////                "INSERT DATA { t:%s a t:Trebovanje; t:datum \"%s\"; t:kolicina \"%s\"; t:rob \"%s\" }",
////                t.getId(), t.getDatum(), t.getKolicina(), t.getRob()
////        );
////
////        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
////            conn.update(insert);
////        }
////
////        return "Trebovanje dodano: " + t.getId();
////    }
////
////    @PutMapping("/{id}")
////    public String update(@PathVariable String id, @RequestBody Trebovanje t) {
////        String delete = String.format(
////                "PREFIX t: <http://www.example.com/trebovanje#> DELETE WHERE { t:%s ?p ?o }", id
////        );
////
////        String insert = String.format(
////                "PREFIX t: <http://www.example.com/trebovanje#> " +
////                "INSERT DATA { t:%s a t:Trebovanje; t:datum \"%s\"; t:kolicina \"%s\"; t:rob \"%s\" }",
////                t.getId(), t.getDatum(), t.getKolicina(), t.getRob()
////        );
////
////        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
////            conn.update(delete);
////            conn.update(insert);
////        }
////
////        return "Trebovanje ažurirano: " + id;
////    }
////
////    @DeleteMapping("/{id}")
////    public String delete(@PathVariable String id) {
////        String delete = String.format(
////                "PREFIX t: <http://www.example.com/trebovanje#> DELETE WHERE { t:%s ?p ?o }", id
////        );
////
////        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
////            conn.update(delete);
////        }
////
////        return "Trebovanje obrisano: " + id;
////    }
////
////}
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
//    // 1. Prikaz svih porudžbina (istorija)
//    @GetMapping
//    public ResponseEntity<List<KancelarijskiMaterijal>> getAll() {
//        return ResponseEntity.ok(service.findAll());
//    }
//
//    // 2. Kreiranje nove porudžbine
//    @PostMapping
//    public ResponseEntity<KancelarijskiMaterijal> create(@RequestBody KancelarijskiMaterijal materijal) {
//        KancelarijskiMaterijal sacuvan = service.save(materijal);
//        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
//    }
//
//    // 3. Brisanje porudžbine
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable String id) {
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // 4. Izmena porudžbine
//    @PutMapping("/{id}")
//    public ResponseEntity<KancelarijskiMaterijal> update(@PathVariable String id,
//                                                         @RequestBody KancelarijskiMaterijal materijal) {
//        KancelarijskiMaterijal azuriran = service.update(id, materijal);
//        return ResponseEntity.ok(azuriran);
//    }
//
//    // 5. Prikaz jedne porudžbine po ID (opciono)
//    @GetMapping("/{id}")
//    public ResponseEntity<KancelarijskiMaterijal> getById(@PathVariable String id) {
//        return service.findAll().stream()
//                .filter(m -> m.getId().equals(id))
//                .findFirst()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
//
//
