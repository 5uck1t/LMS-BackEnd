package com.example.demo.service;

import com.example.demo.dto.IzvestajPolaganjaDTO;
import com.example.demo.dto.IzvestajPolaganjaRowDTO;
import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Polaganje;
import com.example.demo.model.Predmet;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.model.Rok;
import com.example.demo.repository.PolaganjeRepository;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.example.demo.repository.RokRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IzvestajPolaganjaService {

    @Autowired private PrijavaPolaganjaRepository prijavaRepo;
    @Autowired private PolaganjeRepository polaganjeRepo;
    @Autowired private RokRepository rokRepo;
    @Autowired private PredmetRepository predmetRepo;

    // Jednostavno mapiranje bodovi -> ocena (izmeni po tvojoj skali)
    private int ocenaFromBodovi(Double b) {
        if (b == null) return 5;
        if (b >= 91) return 10;
        if (b >= 81) return 9;
        if (b >= 71) return 8;
        if (b >= 61) return 7;
        if (b >= 51) return 6;
        return 5;
        // Ako imaš svoju formulu, zameni ovde.
    }

    public IzvestajPolaganjaDTO getIzvestaj(Long rokId, Long predmetId) {
        Rok rok = rokRepo.findById(rokId).orElseThrow(() -> new IllegalArgumentException("Rok ne postoji: " + rokId));
        Predmet predmet = predmetRepo.findById(predmetId).orElseThrow(() -> new IllegalArgumentException("Predmet ne postoji: " + predmetId));

        // Polaganja za dati rok + predmet:
        // Potrebna je repo metoda tipa:
        // List<Polaganje> findByRok_IdAndEvaluacijaZnanja_RealizacijaPredmeta_Predmet_IdAndObrisanoFalse(Long rokId, Long predmetId)
        List<Polaganje> polaganja = polaganjeRepo.findByRok_IdAndEvaluacijaZnanja_RealizacijaPredmeta_Predmet_IdAndObrisanoFalse(rokId, predmetId);

        // Skupi sve prijave po tim polaganjima
        List<PrijavaPolaganja> prijave = polaganja.isEmpty()
                ? Collections.emptyList()
                : prijavaRepo.findByPolaganje_IdIn(
                        polaganja.stream().map(Polaganje::getId).collect(Collectors.toList())
                  );

        // Mapa za naziv studenta i indeks (ako postoji u modelu)
        List<IzvestajPolaganjaRowDTO> rows = new ArrayList<>();
        for (PrijavaPolaganja pr : prijave) {
            IzvestajPolaganjaRowDTO r = new IzvestajPolaganjaRowDTO();
            r.setPolaganjeId(pr.getPolaganje() != null ? pr.getPolaganje().getId() : null);
            r.setDatum(pr.getDatum());

            String studentName = "-";
            String indeks = "-";
            if (pr.getPohadjanjePredmeta() != null) {
                PohadjanjePredmeta pp = pr.getPohadjanjePredmeta();
                if (pp.getStudentNaGodini() != null && pp.getStudentNaGodini().getStudent() != null) {
                    var o = pp.getStudentNaGodini().getStudent().getOsoba();
                    if (o != null) studentName = (o.getIme() + " " + o.getPrezime()).trim();
                }
                // Ako u StudentNaGodini imaš broj indeksa:
                if (pp.getStudentNaGodini() != null && pp.getStudentNaGodini().getBrojIndeksa() != null) {
                    indeks = pp.getStudentNaGodini().getBrojIndeksa().toString();
                }
            }
            r.setStudent(studentName);
            r.setIndeks(indeks);

            r.setBodovi(pr.getBrojBodova());
            r.setOcena(ocenaFromBodovi(pr.getBrojBodova()));
            rows.add(r);
        }

        // Histogram po oceni
        Map<Integer, Long> histogram = rows.stream()
                .collect(Collectors.groupingBy(IzvestajPolaganjaRowDTO::getOcena, Collectors.counting()));
        // Učini da postoje svi ključevi 5..10
        for (int oc = 5; oc <= 10; oc++) histogram.putIfAbsent(oc, 0L);

        // Agregati
        DoubleSummaryStatistics stats = rows.stream()
                .filter(r -> r.getBodovi() != null)
                .mapToDouble(IzvestajPolaganjaRowDTO::getBodovi)
                .summaryStatistics();

        IzvestajPolaganjaDTO dto = new IzvestajPolaganjaDTO();
        dto.setRokId(rokId);
        dto.setPredmetId(predmetId);
        dto.setRokNaziv(rok.getNaziv());
        dto.setPredmetNaziv(predmet.getNaziv());
        dto.setHistogram(histogram);
        dto.setBrojPrijava((long) rows.size());
        dto.setProsekBodova(stats.getCount() > 0 ? stats.getAverage() : null);
        dto.setMinBodovi(stats.getCount() > 0 ? stats.getMin() : null);
        dto.setMaxBodovi(stats.getCount() > 0 ? stats.getMax() : null);
        dto.setRezultati(rows);
        return dto;
    }
}
