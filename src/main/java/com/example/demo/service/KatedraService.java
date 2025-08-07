package com.example.demo.service;

import com.example.demo.dto.KatedraDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.GodinaStudija;
import com.example.demo.model.Katedra;
import com.example.demo.repository.KatedraRepository;
import com.example.demo.saveDto.KatedraSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KatedraService {

    @Autowired
    private KatedraRepository katedraRepository;

    @Autowired
    private FakultetService fakultetService;

    @Autowired
    private NastavnikService nastavnikService;

    public List<KatedraDTO> findAll() {

        return ((List<Katedra>) katedraRepository.findAll())
                .stream()
                .map(Katedra::toDto)
                .collect(Collectors.toList());
    }

    public List<KatedraDTO> findAllActive() {
        return ((List<Katedra>) katedraRepository.findByObrisanoFalse())
                .stream()
                .map(Katedra::toDto)
                .collect(Collectors.toList());
    }

    public List<KatedraDTO> findAllDeleted() {
        return ((List<Katedra>) katedraRepository.findByObrisanoTrue())
                .stream()
                .map(Katedra::toDto)
                .collect(Collectors.toList());    }

    public List<KatedraDTO> findByFakultetId(Long id) {
        return ((List<Katedra>) katedraRepository.findByFakultet_Id(id))
                .stream()
                .map(Katedra::toDto)
                .collect(Collectors.toList());    }

    public Optional<KatedraDTO> findById(Long id) {
        return katedraRepository.findById(id).map(Katedra::toDto);
    }

    public Optional<Katedra> findEntityById(Long id) {
        return katedraRepository.findById(id);
    }

    public KatedraDTO save(KatedraSaveDTO katedra) {

        Katedra nova = katedra.toEntity();

        nova.setFakultet(fakultetService.findEntityById(katedra.getFakultet_id())
                .orElseThrow(() -> new ResourceNotFoundException("Fakultet with id:" + katedra.getFakultet_id() + " not found")));

        nova.setSefKatedre(nastavnikService.findEntityById(katedra.getSefKatedre_id())
                .orElseThrow(() -> new ResourceNotFoundException("Sef katedre/Nastavnik with id:" + katedra.getSefKatedre_id() + " not found")));

        return katedraRepository.save(nova).toDto();
    }

    public void delete(KatedraDTO katedra) {
        katedra.setObrisano(true);
        katedraRepository.save(katedra.toEntity());
    }

    public void delete(Long id) {
        Optional<Katedra> optional = katedraRepository.findById(id);
        if (optional.isPresent()) {
            Katedra katedra = optional.get();
            katedra.setObrisano(true);
            katedraRepository.save(katedra);
        }
    }

    public void vrati(KatedraDTO katedra) {
        katedra.setObrisano(false);
        katedraRepository.save(katedra.toEntity());
    }

    public void vrati(Long id) {
        Optional<Katedra> optional = katedraRepository.findById(id);
        if (optional.isPresent()) {
            Katedra katedra = optional.get();
            katedra.setObrisano(false);
            katedraRepository.save(katedra);
        }
    }
}