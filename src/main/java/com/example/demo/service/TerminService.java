package com.example.demo.service;

import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.dto.TerminDTO;
import com.example.demo.dto.TipZvanjaDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.model.StudijskiProgram;
import com.example.demo.model.Termin;
import com.example.demo.model.TipZvanja;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.repository.TerminRepository;
import com.example.demo.saveDto.TerminSaveDTO;
import com.example.demo.saveDto.UlogovaniKorisnikSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerminService {

    @Autowired
    private TerminRepository terminRepository;

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    public List<TerminDTO> findAll() {
        return ((List<Termin>) terminRepository.findAll())
                .stream()
                .map(Termin::toDto)
                .collect(Collectors.toList());
    }

    public List<TerminDTO> findAllActive() {
        return ((List<Termin>) terminRepository.findByObrisanoFalse())
                .stream()
                .map(Termin::toDto)
                .collect(Collectors.toList());
    }

    public List<TerminDTO> findAllDeleted() {
        return ((List<Termin>) terminRepository.findByObrisanoTrue())
                .stream()
                .map(Termin::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TerminDTO> findById(Long id) {
        return terminRepository.findById(id).map(Termin::toDto);
    }

    public Optional<Termin> findEntityById(Long id) {
        return terminRepository.findById(id);
    }

    public TerminDTO save(TerminSaveDTO termin) {
        System.out.println("==> Saving Termin:");
        System.out.println("ID: " + termin.getId());
        System.out.println("Datum: " + termin.getDatum());
        System.out.println("VremePocetka: " + termin.getVremePocetka());
        System.out.println("VremeKraja: " + termin.getVremeKraja());
        System.out.println("RealizacijaPredmeta ID: " + termin.getRealizacijaPredmeta_id());

        Termin novi = termin.toEntity();

        // Ako se desi da `termin.getId()` nije null, moraš ga postaviti na entitet:
        if (termin.getId() != null) {
            novi.setId(termin.getId());
        }

        novi.setRealizacijaPredmeta(realizacijaPredmetaService.findEntityById(termin.getRealizacijaPredmeta_id())
                .orElseThrow(() -> new EntityNotFoundException("Realizacija predmeta with id:" + termin.getRealizacijaPredmeta_id() + " not found")));

        return terminRepository.save(novi).toDto();
    }


    public void delete(TerminDTO termin) {
        termin.setObrisano(true);
        terminRepository.save(termin.toEntity());
    }

    public void delete(Long id) {
        Optional<Termin> optional = terminRepository.findById(id);
        if (optional.isPresent()) {
            Termin termin = optional.get();
            termin.setObrisano(true);
            terminRepository.save(termin);
        }
    }

    public void vrati(TerminDTO termin) {
        termin.setObrisano(false);
        terminRepository.save(termin.toEntity());
    }

    public void vrati(Long id) {
        Optional<Termin> optional = terminRepository.findById(id);
        if (optional.isPresent()) {
            Termin termin = optional.get();
            termin.setObrisano(false);
            terminRepository.save(termin);
        }
    }
    public List<TerminDTO> findByRealizacijaPredmeta(Long predmetId) {
        return terminRepository.findByRealizacijaPredmeta_Id(predmetId)
            .stream()
            .map(Termin::toDto)
            .collect(Collectors.toList());
    }

}
