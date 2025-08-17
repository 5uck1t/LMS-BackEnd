package com.example.demo.service;

import com.example.demo.dto.NaucnaOblastDTO;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.repository.NaucnaOblastRepository;
import com.example.demo.saveDto.NaucnaOblastSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NaucnaOblastService {

    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;

    public List<NaucnaOblastDTO> findAll() {
        return ((List<NaucnaOblast>) naucnaOblastRepository.findAll())
                .stream()
                .map(NaucnaOblast::toDto)
                .collect(Collectors.toList());
    }

    public List<NaucnaOblastDTO> findAllActive() {
        return ((List<NaucnaOblast>) naucnaOblastRepository.findByObrisanoFalse())
                .stream()
                .map(NaucnaOblast::toDto)
                .collect(Collectors.toList());
    }

    public List<NaucnaOblastDTO> findAllDeleted() {
        return ((List<NaucnaOblast>) naucnaOblastRepository.findByObrisanoTrue())
                .stream()
                .map(NaucnaOblast::toDto)
                .collect(Collectors.toList());
    }

    public Optional<NaucnaOblastDTO> findById(Long id) {
        return naucnaOblastRepository.findById(id).map(NaucnaOblast::toDto);
    }

    public Optional<NaucnaOblast> findEntityById(Long id) {
        return naucnaOblastRepository.findById(id);
    }

    public NaucnaOblastDTO save(NaucnaOblastSaveDTO naucnaOblast) {
        return naucnaOblastRepository.save(naucnaOblast.toEntity()).toDto();
    }

    public void delete(NaucnaOblastDTO naucnaOblast) {
        naucnaOblast.setObrisano(true);
        naucnaOblastRepository.save(naucnaOblast.toEntity());
    }

    public void delete(Long id) {
        Optional<NaucnaOblast> optional = naucnaOblastRepository.findById(id);
        if (optional.isPresent()) {
            NaucnaOblast naucnaOblast = optional.get();
            naucnaOblast.setObrisano(true);
            naucnaOblastRepository.save(naucnaOblast);
        }
    }

    public void vrati(NaucnaOblastDTO naucnaOblast) {
        naucnaOblast.setObrisano(false);
        naucnaOblastRepository.save(naucnaOblast.toEntity());
    }

    public void vrati(Long id) {
        Optional<NaucnaOblast> optional = naucnaOblastRepository.findById(id);
        if (optional.isPresent()) {
            NaucnaOblast naucnaOblast = optional.get();
            naucnaOblast.setObrisano(false);
            naucnaOblastRepository.save(naucnaOblast);
        }
    }
}