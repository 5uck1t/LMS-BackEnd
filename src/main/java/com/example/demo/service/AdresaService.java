package com.example.demo.service;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Adresa;
import com.example.demo.model.Drzava;
import com.example.demo.repository.AdresaRepository;
import com.example.demo.saveDto.AdresaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdresaService {

    @Autowired
    private AdresaRepository adresaRepository;

    @Autowired
    private MestoService mestoService;

    public List<AdresaDTO> findAll() {
        return ((List<Adresa>) adresaRepository.findAll())
                .stream()
                .map(Adresa::toDto)
                .collect(Collectors.toList());
    }

    public List<AdresaDTO> findAllActive() {

        return ((List<Adresa>) adresaRepository.findByObrisanoFalse())
                .stream()
                .map(Adresa::toDto)
                .collect(Collectors.toList());
    }

    public List<AdresaDTO> findAllDeleted() {

        return ((List<Adresa>) adresaRepository.findByObrisanoTrue())
                .stream()
                .map(Adresa::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AdresaDTO> findById(Long id) {
        return adresaRepository.findById(id).map(Adresa::toDto);
    }

    public Optional<Adresa> findEntityById(Long id) {
        return adresaRepository.findById(id);
    }

    public AdresaDTO save(AdresaSaveDTO adresa) {

        Adresa nova = adresa.toEntity();
        nova.setMesto(mestoService.findEntityById(adresa.getMesto_id())
                .orElseThrow(() -> new ResourceNotFoundException("Mesto with id:" + adresa.getMesto_id() + " not found")));

        return adresaRepository.save(nova).toDto();
    }

    public void delete(AdresaDTO adresa) {
        adresa.setObrisano(true);
        adresaRepository.save(adresa.toEntity());
    }

    public void delete(Long id) {
        Optional<Adresa> optional = adresaRepository.findById(id);
        if (optional.isPresent()) {
            Adresa adresa = optional.get();
            adresa.setObrisano(true);
            adresaRepository.save(adresa);
        }
    }

    public void vrati(AdresaDTO adresa) {
        adresa.setObrisano(false);
        adresaRepository.save(adresa.toEntity());
    }

    public void vrati(Long id) {
        Optional<Adresa> optional = adresaRepository.findById(id);
        if (optional.isPresent()) {
            Adresa adresa = optional.get();
            adresa.setObrisano(false);
            adresaRepository.save(adresa);
        }
    }
}