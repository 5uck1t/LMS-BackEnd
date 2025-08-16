package com.example.demo.service;

import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DatumPredmetaRepository;
import com.example.demo.repository.EvaluacijaZnanjaRepository;
import com.example.demo.saveDto.EvaluacijaZnanjaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvaluacijaZnanjaService {

    @Autowired
    private EvaluacijaZnanjaRepository evaluacijaZnanjaRepository;

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmeta;

    public List<EvaluacijaZnanjaDTO> findAll() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findAll())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluacijaZnanjaDTO> findAllActive() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findByObrisanoFalse())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluacijaZnanjaDTO> findAllDeleted() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findByObrisanoTrue())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EvaluacijaZnanjaDTO> findById(Long id) {
        return evaluacijaZnanjaRepository.findById(id).map(EvaluacijaZnanja::toDto);
    }

    public Optional<EvaluacijaZnanja> findEntityById(Long id) {
        return evaluacijaZnanjaRepository.findById(id);
    }

    public EvaluacijaZnanjaDTO save(EvaluacijaZnanjaSaveDTO evaluacijaZnanja) {

        EvaluacijaZnanja nova = evaluacijaZnanja.toEntity();

        nova.setRealizacijaPredmeta(realizacijaPredmeta.findEntityById(evaluacijaZnanja.getRealizacijaPredmeta_id())
                .orElseThrow(() -> new EntityNotFoundException("Realizacija predmeta with id:" + evaluacijaZnanja.getRealizacijaPredmeta_id() + " not found")));

        return evaluacijaZnanjaRepository.save(nova).toDto();
    }

    public void delete(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        evaluacijaZnanja.setObrisano(true);
        evaluacijaZnanjaRepository.save(evaluacijaZnanja.toEntity());
    }

    public void delete(Long id) {
        Optional<EvaluacijaZnanja> optional = evaluacijaZnanjaRepository.findById(id);
        if (optional.isPresent()) {
            EvaluacijaZnanja evaluacijaZnanja = optional.get();
            evaluacijaZnanja.setObrisano(true);
            evaluacijaZnanjaRepository.save(evaluacijaZnanja);
        }
    }

    public void vrati(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        evaluacijaZnanja.setObrisano(false);
        evaluacijaZnanjaRepository.save(evaluacijaZnanja.toEntity());
    }

    public void vrati(Long id) {
        Optional<EvaluacijaZnanja> optional = evaluacijaZnanjaRepository.findById(id);
        if (optional.isPresent()) {
            EvaluacijaZnanja evaluacijaZnanja = optional.get();
            evaluacijaZnanja.setObrisano(false);
            evaluacijaZnanjaRepository.save(evaluacijaZnanja);
        }
    }
    
    public List<EvaluacijaZnanja> findByPredmetId(Long predmetId) {
        return evaluacijaZnanjaRepository.findByRealizacijaPredmeta_Predmet_IdAndObrisanoFalse(predmetId);
    }
}