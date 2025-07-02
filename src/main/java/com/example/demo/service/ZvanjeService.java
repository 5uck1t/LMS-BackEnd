package com.example.demo.service;

import com.example.demo.dto.ZvanjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Univerzitet;
import com.example.demo.model.Zvanje;
import com.example.demo.repository.ZvanjeRepository;
import com.example.demo.saveDto.ZvanjeSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZvanjeService {

    @Autowired
    private ZvanjeRepository zvanjeRepository;

    @Autowired
    private TipZvanjaService tipZvanjaService;

    @Autowired
    private NaucnaOblastService naucnaOblastService;

    public List<ZvanjeDTO> findAll() {
        return ((List<Zvanje>) zvanjeRepository.findAll())
                .stream()
                .map(Zvanje::toDto)
                .collect(Collectors.toList());
    }

    public List<ZvanjeDTO> findAllActive() {
        return ((List<Zvanje>) zvanjeRepository.findByObrisanoFalse())
                .stream()
                .map(Zvanje::toDto)
                .collect(Collectors.toList());
    }

    public List<ZvanjeDTO> findAllDeleted() {
        return ((List<Zvanje>) zvanjeRepository.findByObrisanoTrue())
                .stream()
                .map(Zvanje::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ZvanjeDTO> findById(Long id) {
        return zvanjeRepository.findById(id).map(Zvanje::toDto);
    }

    public Optional<Zvanje> findEntityById(Long id) {
        return zvanjeRepository.findById(id);
    }

    public ZvanjeDTO save(ZvanjeSaveDTO zvanje) {

        Zvanje novo = zvanje.toEntity();

        novo.setNaucnaOblast(naucnaOblastService.findEntityById(zvanje.getNaucnaOblast_id())
                .orElseThrow(() -> new ResourceNotFoundException("Naucna oblast with id:" + zvanje.getNaucnaOblast_id() + " not found")));

        novo.setTipZvanja(tipZvanjaService.findEntityById(zvanje.getTipZvanja_id())
                .orElseThrow(() -> new ResourceNotFoundException("Tip Zvajna with id:" + zvanje.getTipZvanja_id() + " not found")));

        return zvanjeRepository.save(novo).toDto();
    }

    public void delete(ZvanjeDTO zvanje) {
        zvanje.setObrisano(true);
        zvanjeRepository.save(zvanje.toEntity());
    }

    public void delete(Long id) {
        Optional<Zvanje> optional = zvanjeRepository.findById(id);
        if (optional.isPresent()) {
            Zvanje zvanje = optional.get();
            zvanje.setObrisano(true);
            zvanjeRepository.save(zvanje);
        }
    }

    public void vrati(ZvanjeDTO zvanje) {
        zvanje.setObrisano(false);
        zvanjeRepository.save(zvanje.toEntity());
    }

    public void vrati(Long id) {
        Optional<Zvanje> optional = zvanjeRepository.findById(id);
        if (optional.isPresent()) {
            Zvanje zvanje = optional.get();
            zvanje.setObrisano(false);
            zvanjeRepository.save(zvanje);
        }
    }
}