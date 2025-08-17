package com.example.demo.service;

import com.example.demo.dto.MestoDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Katedra;
import com.example.demo.model.Mesto;
import com.example.demo.repository.MestoRepository;
import com.example.demo.saveDto.MestoSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MestoService {

    @Autowired
    private MestoRepository mestoRepository;

    @Autowired
    private DrzavaService drzavaService;

    public List<MestoDTO> findAll() {
        return ((List<Mesto>) mestoRepository.findAll())
                .stream()
                .map(Mesto::toDto)
                .collect(Collectors.toList());
    }

    public List<MestoDTO> findAllActive() {
        return ((List<Mesto>) mestoRepository.findByObrisanoFalse())
                .stream()
                .map(Mesto::toDto)
                .collect(Collectors.toList());
    }

    public List<MestoDTO> findAllDeleted() {
        return ((List<Mesto>) mestoRepository.findByObrisanoTrue())
                .stream()
                .map(Mesto::toDto)
                .collect(Collectors.toList());    }

    public Optional<MestoDTO> findById(Long id) {
        return mestoRepository.findById(id).map(Mesto::toDto);
    }

    public Optional<Mesto> findEntityById(Long id) {
        return mestoRepository.findById(id);
    }

    public MestoDTO save(MestoSaveDTO mesto) {

        Mesto novo = mesto.toEntity();

        novo.setDrzava(drzavaService.findEntityById(mesto.getDrzava_id())
                .orElseThrow(() -> new ResourceNotFoundException("Drzava with id:" + mesto.getDrzava_id() + " not found")));
        return mestoRepository.save(novo).toDto();
    }

    public void delete(MestoDTO mesto) {
        mesto.setObrisano(true);
        mestoRepository.save(mesto.toEntity());
    }

    public void delete(Long id) {
        Optional<Mesto> optional = mestoRepository.findById(id);
        if (optional.isPresent()) {
            Mesto mesto = optional.get();
            mesto.setObrisano(true);
            mestoRepository.save(mesto);
        }
    }

    public void vrati(MestoDTO mesto) {
        mesto.setObrisano(false);
        mestoRepository.save(mesto.toEntity());
    }

    public void vrati(Long id) {
        Optional<Mesto> optional = mestoRepository.findById(id);
        if (optional.isPresent()) {
            Mesto mesto = optional.get();
            mesto.setObrisano(false);
            mestoRepository.save(mesto);
        }
    }
}