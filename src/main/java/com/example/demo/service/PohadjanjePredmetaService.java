package com.example.demo.service;

import com.example.demo.dto.IstorijaPredmetaDTO;
import com.example.demo.dto.IstorijaStudiranjaDTO;
import com.example.demo.dto.PohadjanjePredmetaDTO;
import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.PohadjanjePredmetaRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.saveDto.PohadjanjePredmetaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PohadjanjePredmetaService {

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    @Autowired
    private StudentNaGodiniService studentNaGodiniService;
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    public List<PohadjanjePredmetaDTO> findAll() {

        return ((List<PohadjanjePredmeta>) pohadjanjePredmetaRepository.findAll())
                .stream()
                .map(PohadjanjePredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<PohadjanjePredmetaDTO> findAllActive() {

        return ((List<PohadjanjePredmeta>) pohadjanjePredmetaRepository.findByObrisanoTrue())
                .stream()
                .map(PohadjanjePredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<PohadjanjePredmetaDTO> findAllDeleted() {
        return ((List<PohadjanjePredmeta>) pohadjanjePredmetaRepository.findByObrisanoFalse())
                .stream()
                .map(PohadjanjePredmeta::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PohadjanjePredmetaDTO> findById(Long id) {
        return pohadjanjePredmetaRepository.findById(id).map(PohadjanjePredmeta::toDto);
    }

    public Optional<PohadjanjePredmeta> findEntityById(Long id) {
        return pohadjanjePredmetaRepository.findById(id);
    }

    public PohadjanjePredmetaDTO save(PohadjanjePredmetaSaveDTO pohadjanjePredmeta) {

        PohadjanjePredmeta novo = pohadjanjePredmeta.toEntity();

        novo.setRealizacijaPredmeta(realizacijaPredmetaService.findEntityById(pohadjanjePredmeta.getRealizacijaPredmeta_id())
                .orElseThrow(() -> new ResourceNotFoundException("Realizacija predmeta with id:" + pohadjanjePredmeta.getRealizacijaPredmeta_id() + " not found")));

        novo.setStudentNaGodini(studentNaGodiniService.findEntityById(pohadjanjePredmeta.getStudentNaGodini_id())
                .orElseThrow(() -> new ResourceNotFoundException("Student na godini with id:" + pohadjanjePredmeta.getStudentNaGodini_id() + " not found")));

        return pohadjanjePredmetaRepository.save(novo).toDto();
    }

    public void delete(PohadjanjePredmetaDTO pohadjanjePredmeta) {
        pohadjanjePredmeta.setObrisano(true);
        pohadjanjePredmetaRepository.save(pohadjanjePredmeta.toEntity());
    }

    public void delete(Long id) {
        Optional<PohadjanjePredmeta> optional = pohadjanjePredmetaRepository.findById(id);
        if (optional.isPresent()) {
            PohadjanjePredmeta pohadjanjePredmeta = optional.get();
            pohadjanjePredmeta.setObrisano(true);
            pohadjanjePredmetaRepository.save(pohadjanjePredmeta);
        }
    }

    public void vrati(PohadjanjePredmetaDTO pohadjanjePredmeta) {
        pohadjanjePredmeta.setObrisano(false);
        pohadjanjePredmetaRepository.save(pohadjanjePredmeta.toEntity());
    }

    public void vrati(Long id) {
        Optional<PohadjanjePredmeta> optional = pohadjanjePredmetaRepository.findById(id);
        if (optional.isPresent()) {
            PohadjanjePredmeta pohadjanjePredmeta = optional.get();
            pohadjanjePredmeta.setObrisano(false);
            pohadjanjePredmetaRepository.save(pohadjanjePredmeta);
        }
    }

    public List<PredmetDTO>findPredmetiByStudentId(Long id){

        return ((List<Predmet>) pohadjanjePredmetaRepository.findPredmetiByStudentId(id))
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<PohadjanjePredmetaDTO>findByStudentNaGodiniId(Long id){

        return ((List<PohadjanjePredmeta>) pohadjanjePredmetaRepository.findByStudentNaGodini_Id(id))
                .stream()
                .map(PohadjanjePredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<PredmetDTO>findPredmetiByStudentIdAndKonacnaOcenaNotNull(Long id){

        return ((List<Predmet>) pohadjanjePredmetaRepository.findPredmetiByStudentIdAndKonacnaOcenaNotNull(id))
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<PredmetDTO>findPredmetiByStudentIdAndKonacnaOcenaIsNull(Long id){

        return ((List<Predmet>) pohadjanjePredmetaRepository.findPredmetiByStudentIdAndKonacnaOcenaIsNull(id))
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentNaGodiniDTO> findStudentiByPredmetId(Long id){
        return ((List<StudentNaGodini>) pohadjanjePredmetaRepository.findStudentsByPredmetId(id))
                .stream()
                .map(StudentNaGodini::toDto)
                .collect(Collectors.toList());
    }
    
    public IstorijaStudiranjaDTO getIstorijaStudenta(Long studentId) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            throw new RuntimeException("Student ne postoji");
        }

        Student student = studentOpt.get();

        List<PohadjanjePredmeta> pohadjanja = pohadjanjePredmetaRepository
            .findByStudentNaGodini_Student_IdAndObrisanoFalse(studentId);

        List<IstorijaPredmetaDTO> predmeti = new ArrayList<>();

        for (PohadjanjePredmeta pohadjanje : pohadjanja) {
            String nazivPredmeta = pohadjanje.getRealizacijaPredmeta().getPredmet().getNaziv();
            int espb = pohadjanje.getRealizacijaPredmeta().getPredmet().getEspb();

            Map<Object, Optional<PrijavaPolaganja>> najnovijePrijaveMap = pohadjanje.getPrijave()
            	    .stream()
            	    .filter(p -> !p.getObrisano() && p.getBrojBodova() != null)
            	    .collect(Collectors.groupingBy(
            	        p -> p.getPolaganje().getEvaluacijaZnanja().getId(),
            	        Collectors.maxBy(Comparator.comparing(PrijavaPolaganja::getDatum))
            	    ));

            	List<PrijavaPolaganja> prijave = najnovijePrijaveMap.values().stream()
            	    .filter(Optional::isPresent)
            	    .map(Optional::get)
            	    .toList();


            int brojPolaganja = prijave.size();
            double ukupnoPoena = prijave.stream().mapToDouble(PrijavaPolaganja::getBrojBodova).sum(); // koristi tvoje polje

            Integer ocena = pohadjanje.getKonacnaOcena();

            predmeti.add(new IstorijaPredmetaDTO(nazivPredmeta, brojPolaganja, ukupnoPoena, espb, ocena));
        }

        return new IstorijaStudiranjaDTO(
            student.getId(),
            student.getOsoba().getIme(),
            student.getOsoba().getPrezime(),
            predmeti
        );
    }

}