package com.example.demo.service;

import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Polaganje;
import com.example.demo.model.Polaganje;
import com.example.demo.repository.PolaganjeRepository;
import com.example.demo.saveDto.PolaganjeSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PolaganjeService {

    @Autowired
    private PolaganjeRepository polaganjeRepository;

    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;

    @Autowired
    private RokService rokService;

    @Autowired
    private DatumPredmetaService datumPredmetaService;

    public List<PolaganjeDTO> findAll() {
        return ((List<Polaganje>) polaganjeRepository.findAll())
                .stream()
                .map(Polaganje::toDto)
                .collect(Collectors.toList());
    }

    public List<PolaganjeDTO> findAllActive() {
        return ((List<Polaganje>) polaganjeRepository.findByObrisanoFalse())
                .stream()
                .map(Polaganje::toDto)
                .collect(Collectors.toList());
    }

    public List<PolaganjeDTO> findAllDeleted() {
        return ((List<Polaganje>) polaganjeRepository.findByObrisanoTrue())
                .stream()
                .map(Polaganje::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PolaganjeDTO> findById(Long id) {
        return polaganjeRepository.findById(id).map(Polaganje::toDto);
    }

    public Optional<Polaganje> findEntityById(Long id) {
        return polaganjeRepository.findById(id);
    }

    public PolaganjeDTO save(PolaganjeSaveDTO polaganje) {

        Polaganje novo = polaganje.toEntity();

        novo.setEvaluacijaZnanja(evaluacijaZnanjaService.findEntityById(polaganje.getEvaluacijaZnanja_id())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacija znanja with id:" + polaganje.getEvaluacijaZnanja_id() + " not found")));

        novo.setRok(rokService.findEntityById(polaganje.getRok_id()).orElseThrow(() -> new ResourceNotFoundException("Rok with id:" + polaganje.getRok_id() + " not found")));


        return polaganjeRepository.save(novo).toDto();
    }

    public void delete(PolaganjeDTO polaganje) {
        polaganje.setObrisano(true);
        polaganjeRepository.save(polaganje.toEntity());
    }

    public void delete(Long id) {
        Optional<Polaganje> optional = polaganjeRepository.findById(id);
        if (optional.isPresent()) {
            Polaganje polaganje = optional.get();
            polaganje.setObrisano(true);
            polaganjeRepository.save(polaganje);
        }
    }

    public void vrati(PolaganjeDTO polaganje) {
        polaganje.setObrisano(false);
        polaganjeRepository.save(polaganje.toEntity());
    }

    public void vrati(Long id) {
        Optional<Polaganje> optional = polaganjeRepository.findById(id);
        if (optional.isPresent()) {
            Polaganje polaganje = optional.get();
            polaganje.setObrisano(false);
            polaganjeRepository.save(polaganje);
        }
    }
}
