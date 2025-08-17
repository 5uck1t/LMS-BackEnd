package com.example.demo.service;

import com.example.demo.dto.DrzavaDTO;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Drzava;
import com.example.demo.repository.DrzavaRepository;
import com.example.demo.saveDto.DrzavaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrzavaService {

    @Autowired
    private DrzavaRepository drzavaRepository;

    public List<DrzavaDTO> findAll() {

        return ((List<Drzava>) drzavaRepository.findAll())
                .stream()
                .map(Drzava::toDto)
                .collect(Collectors.toList());
    }

    public List<DrzavaDTO> findAllActive() {

        return ((List<Drzava>) drzavaRepository.findByObrisanoFalse())
                .stream()
                .map(Drzava::toDto)
                .collect(Collectors.toList());
    }

    public List<DrzavaDTO> findAllDeleted() {

        return ((List<Drzava>) drzavaRepository.findByObrisanoTrue())
                .stream()
                .map(Drzava::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DrzavaDTO> findById(Long id) {
        return drzavaRepository.findById(id).map(Drzava::toDto);
    }

    public Optional<Drzava> findEntityById(Long id) {
        return drzavaRepository.findById(id);
    }

    public DrzavaDTO save(DrzavaSaveDTO drzava) {
        return drzavaRepository.save(drzava.toEntity()).toDto();
    }

    public void delete(DrzavaDTO drzava) {
        drzava.setObrisano(true);
        drzavaRepository.save(drzava.toEntity());
    }

    public void delete(Long id) {
        Optional<Drzava> optional = drzavaRepository.findById(id);
        if (optional.isPresent()) {
            Drzava drzava = optional.get();
            drzava.setObrisano(true);
            drzavaRepository.save(drzava);
        }
    }

    public void vrati(DrzavaDTO drzava) {
        drzava.setObrisano(false);
        drzavaRepository.save(drzava.toEntity());
    }

    public void vrati(Long id) {
        Optional<Drzava> optional = drzavaRepository.findById(id);
        if (optional.isPresent()) {
            Drzava drzava = optional.get();
            drzava.setObrisano(false);
            drzavaRepository.save(drzava);
        }
    }
}