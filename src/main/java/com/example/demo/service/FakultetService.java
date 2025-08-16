package com.example.demo.service;

import com.example.demo.dto.FakultetDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Adresa;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.EvaluacijaZnanja;
import com.example.demo.model.Fakultet;
import com.example.demo.repository.FakultetRepository;
import com.example.demo.saveDto.FakultetSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FakultetService {

    @Autowired
    private FakultetRepository fakultetRepository;

    @Autowired
    private UniverzitetService univerzitetService;

    @Autowired
    private AdresaService adresaService;

    @Autowired
    @Lazy
    private NastavnikService nastavnikService;

    public List<FakultetDTO> findAll() {

        return ((List<Fakultet>) fakultetRepository.findByObrisanoTrue())
                .stream()
                .map(Fakultet::toDto)
                .collect(Collectors.toList());
    }

    public List<FakultetDTO> findAllActive() {
        return ((List<Fakultet>) fakultetRepository.findByObrisanoFalse())
                .stream()
                .map(Fakultet::toDto)
                .collect(Collectors.toList());
    }

    public List<FakultetDTO> findAllDeleted() {

        return ((List<Fakultet>) fakultetRepository.findByObrisanoTrue())
                .stream()
                .map(Fakultet::toDto)
                .collect(Collectors.toList());
    }

    public Optional<FakultetDTO> findById(Long id) {
        return fakultetRepository.findById(id).map(Fakultet::toDto);
    }

    public Optional<Fakultet> findEntityById(Long id) {
        return fakultetRepository.findById(id);
    }

    public FakultetDTO save(FakultetSaveDTO fakultet) {

        Fakultet novi = fakultet.toEntity();
        novi.setDekan(nastavnikService.findEntityById(fakultet.getDekan_id())
                .orElseThrow(() -> new ResourceNotFoundException("Dekan/Nastavnik with id:" + fakultet.getDekan_id() + " not found")));

        novi.setAdresa(adresaService.findEntityById(fakultet.getAdresa_id())
                .orElseThrow(() -> new ResourceNotFoundException("Adresa with id:" + fakultet.getAdresa_id() + " not found")));

        novi.setUniverzitet(univerzitetService.findEntityById(fakultet.getUniverzitet_id())
                .orElseThrow(() -> new ResourceNotFoundException("Rok with id:" + fakultet.getUniverzitet_id() + " not found")));
        return fakultetRepository.save(novi).toDto();
    }

    public void delete(FakultetDTO fakultet) {
        fakultet.setObrisano(true);
        fakultetRepository.save(fakultet.toEntity());
    }

    public void delete(Long id) {
        Optional<Fakultet> optional = fakultetRepository.findById(id);
        if (optional.isPresent()) {
            Fakultet fakultet = optional.get();
            fakultet.setObrisano(true);
            fakultetRepository.save(fakultet);
        }
    }

    public void vrati(FakultetDTO fakultet) {
        fakultet.setObrisano(false);
        fakultetRepository.save(fakultet.toEntity());
    }

    public void vrati(Long id) {
        Optional<Fakultet> optional = fakultetRepository.findById(id);
        if (optional.isPresent()) {
            Fakultet fakultet = optional.get();
            fakultet.setObrisano(false);
            fakultetRepository.save(fakultet);
        }
    }
}