package com.example.demo.service;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.model.Predmet;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.saveDto.PredmetSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PredmetService {

    @Autowired
    private PredmetRepository predmetRepository;

    public List<PredmetDTO> findAll() {

        return ((List<Predmet>) predmetRepository.findAll())
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<PredmetDTO> findAllActive() {

        return ((List<Predmet>) predmetRepository.findByObrisanoFalse())
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<PredmetDTO> findAllDeleted() {
        return ((List<Predmet>) predmetRepository.findByObrisanoTrue())
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PredmetDTO> findById(Long id) {
        return predmetRepository.findById(id).map(Predmet::toDto);
    }

    public PredmetDTO save(PredmetSaveDTO predmet) {
        return predmetRepository.save(predmet.toEntity()).toDto();
    }

    public void delete(PredmetDTO predmet) {
        predmet.setObrisano(true);
        predmetRepository.save(predmet.toEntity());
    }

    public void delete(Long id) {
        Optional<Predmet> optional = predmetRepository.findById(id);
        if (optional.isPresent()) {
            Predmet predmet = optional.get();
            predmet.setObrisano(true);
            predmetRepository.save(predmet);
        }
    }

    public void vrati(PredmetDTO predmet) {
        predmet.setObrisano(false);
        predmetRepository.save(predmet.toEntity());
    }

    public void vrati(Long id) {
        Optional<Predmet> optional = predmetRepository.findById(id);
        if (optional.isPresent()) {
            Predmet predmet = optional.get();
            predmet.setObrisano(false);
            predmetRepository.save(predmet);
        }
    }
}