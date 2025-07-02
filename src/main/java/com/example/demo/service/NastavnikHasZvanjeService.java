package com.example.demo.service;

import com.example.demo.dto.NastavnikHasZvanjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Mesto;
import com.example.demo.model.NastavnikHasZvanje;
import com.example.demo.repository.NastavnikHasZvanjeRepository;
import com.example.demo.saveDto.NastavnikHasZvanjeSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NastavnikHasZvanjeService {

    @Autowired
    private NastavnikHasZvanjeRepository nastavnikHasZvanjeRepository;

    @Autowired
    private NastavnikService nastavnikService;

    @Autowired
    private ZvanjeService zvanjeService;

    public List<NastavnikHasZvanjeDTO> findAll() {
        return ((List<NastavnikHasZvanje>) nastavnikHasZvanjeRepository.findAll())
                .stream()
                .map(NastavnikHasZvanje::toDto)
                .collect(Collectors.toList());
    }

    public List<NastavnikHasZvanjeDTO> findAllActive() {
        return ((List<NastavnikHasZvanje>) nastavnikHasZvanjeRepository.findByObrisanoFalse())
                .stream()
                .map(NastavnikHasZvanje::toDto)
                .collect(Collectors.toList());
    }

    public List<NastavnikHasZvanjeDTO> findAllDeleted() {
        return ((List<NastavnikHasZvanje>) nastavnikHasZvanjeRepository.findByObrisanoTrue())
                .stream()
                .map(NastavnikHasZvanje::toDto)
                .collect(Collectors.toList());
    }

    public Optional<NastavnikHasZvanjeDTO> findById(Long id) {
        return nastavnikHasZvanjeRepository.findById(id).map(NastavnikHasZvanje::toDto);
    }

    public Optional<NastavnikHasZvanje> findEntityById(Long id) {
        return nastavnikHasZvanjeRepository.findById(id);
    }

    public NastavnikHasZvanjeDTO save(NastavnikHasZvanjeSaveDTO nastavnikHasZvanje) {

        NastavnikHasZvanje novo = nastavnikHasZvanje.toEntity();

        novo.setNastavnik(nastavnikService.findEntityById(nastavnikHasZvanje.getNastavnik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik with id:" + nastavnikHasZvanje.getNastavnik_id() + " not found")));

        novo.setZvanje(zvanjeService.findEntityById(nastavnikHasZvanje.getZvanje_id())
                .orElseThrow(() -> new ResourceNotFoundException("Zvanje with id:" + nastavnikHasZvanje.getZvanje_id() + " not found")));

        return nastavnikHasZvanjeRepository.save(novo).toDto();
    }

    public void delete(NastavnikHasZvanjeDTO nastavnikHasZvanje) {
        nastavnikHasZvanje.setObrisano(true);
        nastavnikHasZvanjeRepository.save(nastavnikHasZvanje.toEntity());
    }

    public void delete(Long id) {
        Optional<NastavnikHasZvanje> optional = nastavnikHasZvanjeRepository.findById(id);
        if (optional.isPresent()) {
            NastavnikHasZvanje nastavnikHasZvanje = optional.get();
            nastavnikHasZvanje.setObrisano(true);
            nastavnikHasZvanjeRepository.save(nastavnikHasZvanje);
        }
    }

    public void vrati(NastavnikHasZvanjeDTO nastavnikHasZvanje) {
        nastavnikHasZvanje.setObrisano(false);
        nastavnikHasZvanjeRepository.save(nastavnikHasZvanje.toEntity());
    }

    public void vrati(Long id) {
        Optional<NastavnikHasZvanje> optional = nastavnikHasZvanjeRepository.findById(id);
        if (optional.isPresent()) {
            NastavnikHasZvanje nastavnikHasZvanje = optional.get();
            nastavnikHasZvanje.setObrisano(false);
            nastavnikHasZvanjeRepository.save(nastavnikHasZvanje);
        }
    }
}