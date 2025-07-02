package com.example.demo.service;

import com.example.demo.dto.TipZvanjaDTO;
import com.example.demo.model.StudijskiProgram;
import com.example.demo.model.TipZvanja;
import com.example.demo.repository.TipZvanjaRepository;
import com.example.demo.saveDto.TipZvanjaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipZvanjaService {

    @Autowired
    private TipZvanjaRepository tipZvanjaRepository;

    public List<TipZvanjaDTO> findAll() {
        return ((List<TipZvanja>) tipZvanjaRepository.findAll())
                .stream()
                .map(TipZvanja::toDto)
                .collect(Collectors.toList());
    }

    public List<TipZvanjaDTO> findAllActive() {
        return ((List<TipZvanja>) tipZvanjaRepository.findByObrisanoFalse())
                .stream()
                .map(TipZvanja::toDto)
                .collect(Collectors.toList());
    }

    public List<TipZvanjaDTO> findAllDeleted() {
        return ((List<TipZvanja>) tipZvanjaRepository.findByObrisanoTrue())
                .stream()
                .map(TipZvanja::toDto)
                .collect(Collectors.toList());
    }

    public Optional<TipZvanjaDTO> findById(Long id) {
        return tipZvanjaRepository.findById(id).map(TipZvanja::toDto);
    }

    public TipZvanjaDTO save(TipZvanjaSaveDTO tipZvanja) {
        return tipZvanjaRepository.save(tipZvanja.toEntity()).toDto();
    }

    public void delete(TipZvanjaDTO tipZvanja) {
        tipZvanja.setObrisano(true);
        tipZvanjaRepository.save(tipZvanja.toEntity());
    }

    public void delete(Long id) {
        Optional<TipZvanja> optional = tipZvanjaRepository.findById(id);
        if (optional.isPresent()) {
            TipZvanja tipZvanja = optional.get();
            tipZvanja.setObrisano(true);
            tipZvanjaRepository.save(tipZvanja);
        }
    }

    public void vrati(TipZvanjaDTO tipZvanja) {
        tipZvanja.setObrisano(false);
        tipZvanjaRepository.save(tipZvanja.toEntity());
    }

    public void vrati(Long id) {
        Optional<TipZvanja> optional = tipZvanjaRepository.findById(id);
        if (optional.isPresent()) {
            TipZvanja tipZvanja = optional.get();
            tipZvanja.setObrisano(false);
            tipZvanjaRepository.save(tipZvanja);
        }
    }
}