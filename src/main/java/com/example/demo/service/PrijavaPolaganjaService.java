package com.example.demo.service;

import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Polaganje;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Rok;
import com.example.demo.model.Student;
import com.example.demo.repository.PohadjanjePredmetaRepository;
import com.example.demo.repository.PolaganjeRepository;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.saveDto.PrijavaPolaganjaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrijavaPolaganjaService {

    @Autowired
    private PrijavaPolaganjaRepository prijavaPolaganjaRepository;

    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;
    
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    @Autowired
    private PolaganjeService polaganjeService;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PolaganjeRepository polaganjeRepository;

    
    public List<PrijavaPolaganjaDTO> findAll() {
        return ((List<PrijavaPolaganja>) prijavaPolaganjaRepository.findAll())
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }

    public List<PrijavaPolaganjaDTO> findAllActive() {
        return ((List<PrijavaPolaganja>) prijavaPolaganjaRepository.findByObrisanoFalse())
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }

    public List<PrijavaPolaganjaDTO> findAllDeleted() {
        return ((List<PrijavaPolaganja>) prijavaPolaganjaRepository.findByObrisanoTrue())
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }

    public List<PrijavaPolaganjaDTO> findByPolaganjeId(Long id) {
        return ((List<PrijavaPolaganja>) prijavaPolaganjaRepository.findByPolaganje_Id(id))
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }

    public List<PrijavaPolaganjaDTO> findByPohadjanjeId(Long id) {
        return ((List<PrijavaPolaganja>) prijavaPolaganjaRepository.findByPohadjanjePredmeta_Id(id))
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PrijavaPolaganjaDTO> findById(Long id) {
        return prijavaPolaganjaRepository.findById(id).map(PrijavaPolaganja::toDto);
    }

    public Optional<PrijavaPolaganja> findEntityById(Long id) {
        return prijavaPolaganjaRepository.findById(id);
    }
    
    public List<PolaganjeDTO> findDostupnaPolaganja(Long studentId) {
        List<PohadjanjePredmeta> pohadjanja = pohadjanjePredmetaRepository.findByStudentNaGodiniStudentIdAndObrisanoFalse(studentId);

        List<Long> predmetIds = pohadjanja.stream()
                .map(pp -> pp.getRealizacijaPredmeta().getPredmet().getId())
                .collect(Collectors.toList());

        List<Polaganje> svaPolaganja = polaganjeRepository.findByEvaluacijaZnanja_RealizacijaPredmeta_Predmet_IdInAndObrisanoFalse(predmetIds);

        List<Long> vecPrijavljenaPolaganja = prijavaPolaganjaRepository.findByPohadjanjePredmetaStudentNaGodiniStudentId(studentId)
                .stream()
                .map(p -> p.getPolaganje().getId())
                .collect(Collectors.toList());

        return svaPolaganja.stream()
                .filter(p -> !vecPrijavljenaPolaganja.contains(p.getId()))
                .map(Polaganje::toDto)
                .collect(Collectors.toList());
    }


    public PrijavaPolaganjaDTO save(PrijavaPolaganjaSaveDTO prijava) {

        PrijavaPolaganja nova = prijava.toEntity();

        nova.setPohadjanjePredmeta(pohadjanjePredmetaService.findEntityById(prijava.getPohadjanjePredmeta_id())
                .orElseThrow(() -> new ResourceNotFoundException("Pohadjanje predmeta with id:" + prijava.getPohadjanjePredmeta_id() + " not found")));

        nova.setPolaganje(polaganjeService.findEntityById(prijava.getPolaganje_id())
                .orElseThrow(() -> new ResourceNotFoundException("Polaganje with id:" + prijava.getPolaganje_id() + " not found")));

        return prijavaPolaganjaRepository.save(nova).toDto();
    }

    public void delete(PrijavaPolaganjaDTO prijavaPolaganja) {
        prijavaPolaganja.setObrisano(true);
        prijavaPolaganjaRepository.save(prijavaPolaganja.toEntity());
    }

    public void delete(Long id) {
        Optional<PrijavaPolaganja> optional = prijavaPolaganjaRepository.findById(id);
        if (optional.isPresent()) {
            PrijavaPolaganja prijavaPolaganja = optional.get();
            prijavaPolaganja.setObrisano(true);
            prijavaPolaganjaRepository.save(prijavaPolaganja);
        }
    }

    public void vrati(PrijavaPolaganjaDTO prijavaPolaganja) {
        prijavaPolaganja.setObrisano(false);
        prijavaPolaganjaRepository.save(prijavaPolaganja.toEntity());
    }

    public void vrati(Long id) {
        Optional<PrijavaPolaganja> optional = prijavaPolaganjaRepository.findById(id);
        if (optional.isPresent()) {
            PrijavaPolaganja prijavaPolaganja = optional.get();
            prijavaPolaganja.setObrisano(false);
            prijavaPolaganjaRepository.save(prijavaPolaganja);
        }
    }
    
    public void prijaviIspit(Long studentId, Long polaganjeId) {
        System.out.println("=== START prijaviIspit ===");
        System.out.println("Student ID: " + studentId);
        System.out.println("Polaganje ID: " + polaganjeId);

        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));

        Polaganje polaganje = polaganjeRepository.findById(polaganjeId)
            .orElseThrow(() -> new RuntimeException("Polaganje nije pronađeno"));

        Rok rok = polaganje.getRok();
        Date danas = new Date();

        System.out.println("Rok: " + rok.getNaziv() + ", Početak: " + rok.getPocetak() + ", Kraj: " + rok.getKraj());

        if (rok.getPocetak().after(danas) || rok.getKraj().before(danas)) {
            throw new RuntimeException("Prijava za ispit nije trenutno aktivna (rok nije u toku)");
        }

        RealizacijaPredmeta realizacijaPredmeta = polaganje.getEvaluacijaZnanja().getRealizacijaPredmeta();
        System.out.println("RealizacijaPredmeta ID: " + realizacijaPredmeta.getId());

        // Log svih pohadjanja studenta
        System.out.println("Broj godina koje student ima: " + student.getStudentNaGodini().size());
        for (var godina : student.getStudentNaGodini()) {
            System.out.println("Godina ID: " + godina.getId());
            for (var pohadjanje : godina.getPohadjanjaPredmeta()) {
                if (!pohadjanje.getObrisano()) {
                    System.out.println("  Pohadjanje ID: " + pohadjanje.getId()
                        + ", Realizacija ID: " + pohadjanje.getRealizacijaPredmeta().getId());
                }
            }
        }

        PohadjanjePredmeta pohadjanje = student.getStudentNaGodini()
            .stream()
            .flatMap(godina -> godina.getPohadjanjaPredmeta().stream())
            .filter(pp -> !pp.getObrisano() && pp.getRealizacijaPredmeta().equals(realizacijaPredmeta))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Student ne sluša ovaj predmet"));

        System.out.println("Pronađeno pohadjanje ID: " + pohadjanje.getId());

        boolean vecPrijavljen = prijavaPolaganjaRepository
            .existsByPohadjanjePredmetaAndPolaganje(pohadjanje, polaganje);

        if (vecPrijavljen) {
            throw new RuntimeException("Student je već prijavljen na ovo polaganje");
        }

        PrijavaPolaganja prijava = new PrijavaPolaganja();
        prijava.setDatum(new Date());
        prijava.setPolaganje(polaganje);
        prijava.setPohadjanjePredmeta(pohadjanje);
        prijava.setBrojBodova(null);
        prijava.setObrisano(false);

        prijavaPolaganjaRepository.save(prijava);

        System.out.println("=== KRAJ prijaviIspit: Uspešna prijava ===");
    }
    
    
    public List<PrijavaPolaganjaDTO> findByStudentId(Long studentId) {
        return prijavaPolaganjaRepository.findByPohadjanjePredmeta_StudentNaGodini_Student_Id(studentId)
                .stream()
                .map(PrijavaPolaganja::toDto)
                .collect(Collectors.toList());
    }
    
    public List<PrijavaPolaganjaDTO> findAllForStudentWithin15Days(Long studentId) {
        List<PrijavaPolaganjaDTO> svePrijave = findByStudentId(studentId);
        LocalDateTime granica = LocalDateTime.now().minusDays(15);

        return svePrijave.stream()
            .filter(prijava -> {
                Date datumPolaganjaRaw = prijava.getPolaganje().getDatum();
                if (datumPolaganjaRaw == null) {
                    return false;
                }

                LocalDateTime datumPolaganja = datumPolaganjaRaw.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

                return datumPolaganja.isAfter(granica);
            })
            .collect(Collectors.toList());
    }



    
    
}
