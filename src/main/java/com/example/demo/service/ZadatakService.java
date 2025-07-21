package com.example.demo.service;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.ZadatakDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zadatak;
import com.example.demo.repository.ZadatakRepository;
import com.example.demo.saveDto.PredmetSaveDTO;
import com.example.demo.saveDto.ZadatakSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZadatakService {

    @Autowired
    private ZadatakRepository zadatakRepository;

    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;

    public List<ZadatakDTO> findAll() {
        return ((List<Zadatak>) zadatakRepository.findAll())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findAllActive() {
        return ((List<Zadatak>) zadatakRepository.findByObrisanoFalse())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findAllDeleted() {
        return ((List<Zadatak>) zadatakRepository.findByObrisanoTrue())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findByEvaluacijaId(Long id) {
        return ((List<Zadatak>) zadatakRepository.findByEvaluacijaZnanja_Id(id))
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ZadatakDTO> findById(Long id) {
        return zadatakRepository.findById(id).map(Zadatak::toDto);
    }

    public Optional<Zadatak> findEntityById(Long id) {
        return zadatakRepository.findById(id);
    }

    public ZadatakDTO save(ZadatakSaveDTO zadatak) {

        Zadatak novi = zadatak.toEntity();

        novi.setEvaluacijaZnanja(evaluacijaZnanjaService.findEntityById(zadatak.getEvaluacijaZnanja_id())
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacija znanja with id:" + zadatak.getEvaluacijaZnanja_id() + " not found")));

        return zadatakRepository.save(novi).toDto();
    }

    public void delete(ZadatakDTO zadatak) {
        zadatak.setObrisano(true);
        zadatakRepository.save(zadatak.toEntity());
    }

    public void delete(Long id) {
        Optional<Zadatak> optional = zadatakRepository.findById(id);
        if (optional.isPresent()) {
            Zadatak zadatak = optional.get();
            zadatak.setObrisano(true);
            zadatakRepository.save(zadatak);
        }
    }

    public void vrati(ZadatakDTO zadatak) {
        zadatak.setObrisano(false);
        zadatakRepository.save(zadatak.toEntity());
    }

    public void vrati(Long id) {
        Optional<Zadatak> optional = zadatakRepository.findById(id);
        if (optional.isPresent()) {
            Zadatak zadatak = optional.get();
            zadatak.setObrisano(false);
            zadatakRepository.save(zadatak);
        }
    }
}
