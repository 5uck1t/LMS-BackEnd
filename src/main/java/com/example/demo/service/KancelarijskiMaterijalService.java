//package com.example.demo.service;
//
//import com.example.demo.model.KancelarijskiMaterijal;
//import org.apache.jena.query.*;
//import org.apache.jena.rdfconnection.RDFConnection;
//import org.apache.jena.rdfconnection.RDFConnectionFuseki;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class KancelarijskiMaterijalService {
//
//    private static final String FUSEKI_URL = "http://localhost:3030/projekat"; // promijeni po potrebi
//    private static final String PREFIX = "PREFIX km: <http://www.example.com/kancelarijskiMaterijal#> ";
//
//    public List<KancelarijskiMaterijal> findAll() {
//        List<KancelarijskiMaterijal> results = new ArrayList<>();
//
//        String queryStr = PREFIX +
//                "SELECT ?id ?naziv ?kolicina ?opis ?datum ?radnik ?status WHERE { " +
//                "?id a km:KancelarijskiMaterijal; " +
//                "km:naziv ?naziv; " +
//                "km:kolicina ?kolicina; " +
//                "km:opis ?opis; " +
//                "km:datumNarudzbine ?datum; " +
//                "km:radnik ?radnik; " +
//                "km:status ?status }";
//
//        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
//            conn.querySelect(queryStr, qs -> {
//                String id = qs.getResource("id").getLocalName();
//                String naziv = qs.getLiteral("naziv").getString();
//                int kolicina = qs.getLiteral("kolicina").getInt();
//                String opis = qs.getLiteral("opis").getString();
//                String datum = qs.getLiteral("datum").getString();
//                String radnik = qs.getLiteral("radnik").getString();
//                String status = qs.getLiteral("status").getString();
//
//                results.add(new KancelarijskiMaterijal(id, naziv, kolicina, opis, datum, radnik, status));
//            });
//        }
//
//        return results;
//    }
//
//    public KancelarijskiMaterijal save(KancelarijskiMaterijal materijal) {
//        if (materijal.getId() == null || materijal.getId().isEmpty()) {
//            materijal.setId("KM" + UUID.randomUUID().toString().replace("-", ""));
//        }
//
//        String insert = PREFIX + "INSERT DATA { " +
//                "km:" + materijal.getId() + " a km:KancelarijskiMaterijal; " +
//                "km:naziv \"" + materijal.getNaziv() + "\"; " +
//                "km:kolicina " + materijal.getKolicina() + "; " +
//                "km:opis \"" + materijal.getOpis() + "\"; " +
//                "km:datumNarudzbine \"" + materijal.getDatumNarudzbine() + "\"; " +
//                "km:radnik \"" + materijal.getRadnik() + "\"; " +
//                "km:status \"" + materijal.getStatus() + "\" }";
//
//        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
//            conn.update(insert);
//        }
//
//        return materijal;
//    }
//
//    public void delete(String id) {
//        String delete = PREFIX + "DELETE WHERE { km:" + id + " ?p ?o }";
//        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
//            conn.update(delete);
//        }
//    }
//}
package com.example.demo.service;

import com.example.demo.model.KancelarijskiMaterijal;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFuseki;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KancelarijskiMaterijalService {

    private static final String FUSEKI_URL = "http://localhost:3030/projekat";
    private static final String PREFIX = "PREFIX km: <http://www.example.com/kancelarijskiMaterijal#> ";

    public List<KancelarijskiMaterijal> findAll() {
        List<KancelarijskiMaterijal> results = new ArrayList<>();

        String queryStr = PREFIX +
                "SELECT ?id ?naziv ?kolicina ?opis ?datum ?radnik ?status WHERE { " +
                "?id a km:KancelarijskiMaterijal; " +
                "km:naziv ?naziv; " +
                "km:kolicina ?kolicina; " +
                "km:opis ?opis; " +
                "km:datumNarudzbine ?datum; " +
                "km:radnik ?radnik; " +
                "km:status ?status }";

        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
            conn.querySelect(queryStr, qs -> {
                String id = qs.getResource("id").getLocalName();
                String naziv = qs.getLiteral("naziv").getString();
                int kolicina = qs.getLiteral("kolicina").getInt();
                String opis = qs.getLiteral("opis").getString();
                String datum = qs.getLiteral("datum").getString();
                String radnik = qs.getLiteral("radnik").getString();
                String status = qs.getLiteral("status").getString();

                results.add(new KancelarijskiMaterijal(naziv, kolicina, opis, datum, radnik, status));
                results.get(results.size() - 1).setId(id); // postavimo pravi ID iz Fuseki
            });
        }

        return results;
    }

    public KancelarijskiMaterijal save(KancelarijskiMaterijal materijal) {
        if (materijal.getId() == null || materijal.getId().isEmpty()) {
            materijal.setId("KM" + java.util.UUID.randomUUID().toString().replace("-", ""));
        }

        String insert = PREFIX + "INSERT DATA { " +
                "km:" + materijal.getId() + " a km:KancelarijskiMaterijal; " +
                "km:naziv \"" + materijal.getNaziv() + "\"; " +
                "km:kolicina " + materijal.getKolicina() + "; " +
                "km:opis \"" + materijal.getOpis() + "\"; " +
                "km:datumNarudzbine \"" + materijal.getDatumNarudzbine() + "\"; " +
                "km:radnik \"" + materijal.getRadnik() + "\"; " +
                "km:status \"" + materijal.getStatus() + "\" }";

        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
            conn.update(insert);
        }

        return materijal;
    }

    public KancelarijskiMaterijal update(String id, KancelarijskiMaterijal materijal) {
        // prvo obrišemo postojeći zapis
        delete(id);

        // postavimo ID tako da ostane isti
        materijal.setId(id);

        // ubacimo novi zapis sa izmenjenim podacima
        return save(materijal);
    }

    public void delete(String id) {
        String delete = PREFIX + "DELETE WHERE { km:" + id + " ?p ?o }";
        try (RDFConnection conn = RDFConnectionFuseki.connect(FUSEKI_URL)) {
            conn.update(delete);
        }
    }
}
