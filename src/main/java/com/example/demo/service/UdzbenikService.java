package com.example.demo.service;

import com.example.demo.dto.UdzbenikDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Udzbenik;
import com.example.demo.repository.UdzbenikRepository;
import com.example.demo.saveDto.UdzbenikSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UdzbenikService {

    @Autowired
    private UdzbenikRepository udzbenikRepository;

    public List<UdzbenikDTO> findAll() {
        return StreamSupport.stream(udzbenikRepository.findAll().spliterator(), false)
                .map(Udzbenik::toDto)
                .collect(Collectors.toList());
    }

    public List<UdzbenikDTO> findAllActive() {
        return udzbenikRepository.findByObrisanoFalse()
                .stream().map(Udzbenik::toDto).collect(Collectors.toList());
    }

    public List<UdzbenikDTO> findAllDeleted() {
        return udzbenikRepository.findByObrisanoTrue()
                .stream().map(Udzbenik::toDto).collect(Collectors.toList());
    }

    public Optional<UdzbenikDTO> findById(Long id) {
        return udzbenikRepository.findById(id).map(Udzbenik::toDto);
    }

    public Optional<Udzbenik> findEntityById(Long id) {
        return udzbenikRepository.findById(id);
    }

    public UdzbenikDTO save(UdzbenikSaveDTO dto) {
        Udzbenik e = dto.toEntity();
        return udzbenikRepository.save(e).toDto();
    }

    public void delete(Long id) {
        udzbenikRepository.findById(id).ifPresent(e -> {
            e.setObrisano(true);
            udzbenikRepository.save(e);
        });
    }

    public void vrati(Long id) {
        udzbenikRepository.findById(id).ifPresent(e -> {
            e.setObrisano(false);
            udzbenikRepository.save(e);
        });
    }
}
