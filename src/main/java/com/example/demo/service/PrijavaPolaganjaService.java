package com.example.demo.service;

import com.example.demo.dto.PrijavaPolaganjaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.example.demo.saveDto.PrijavaPolaganjaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrijavaPolaganjaService {

    @Autowired
    private PrijavaPolaganjaRepository prijavaPolaganjaRepository;

    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    @Autowired
    private PolaganjeService polaganjeService;

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
}
