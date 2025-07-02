package com.example.demo.service;

import com.example.demo.dto.PohadjanjePredmetaDTO;
import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Osoba;
import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Predmet;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.repository.PohadjanjePredmetaRepository;
import com.example.demo.saveDto.PohadjanjePredmetaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PohadjanjePredmetaService {

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

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

    public PohadjanjePredmetaDTO save(PohadjanjePredmetaSaveDTO pohadjanjePredmeta) {

        PohadjanjePredmeta novo = pohadjanjePredmeta.toEntity();

        novo.setRealizacijaPredmeta(realizacijaPredmetaService.findById(pohadjanjePredmeta.getRealizacijaPredmeta_id())
                .orElseThrow(() -> new ResourceNotFoundException("Realizacija predmeta with id:" + pohadjanjePredmeta.getRealizacijaPredmeta_id() + " not found")).toEntity());

        novo.setStudentNaGodini(studentNaGodiniService.findById(pohadjanjePredmeta.getStudentNaGodini_id())
                .orElseThrow(() -> new ResourceNotFoundException("Student na godini with id:" + pohadjanjePredmeta.getStudentNaGodini_id() + " not found")).toEntity());

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
}