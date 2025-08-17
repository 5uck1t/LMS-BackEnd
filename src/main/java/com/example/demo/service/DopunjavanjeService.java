package com.example.demo.service;

import com.example.demo.dto.DopunjavanjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Dopunjavanje;
import com.example.demo.model.Udzbenik;
import com.example.demo.repository.DopunjavanjeRepository;
import com.example.demo.saveDto.DopunjavanjeSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DopunjavanjeService {

    @Autowired
    private DopunjavanjeRepository dopunjavanjeRepository;

    @Autowired
    private UdzbenikService udzbenikService;

    public List<DopunjavanjeDTO> findAll() {
        return StreamSupport.stream(dopunjavanjeRepository.findAll().spliterator(), false)
                .map(Dopunjavanje::toDto)
                .collect(Collectors.toList());
    }

    public List<DopunjavanjeDTO> findAllActive() {
        return dopunjavanjeRepository.findByObrisanoFalse()
                .stream().map(Dopunjavanje::toDto).collect(Collectors.toList());
    }

    public List<DopunjavanjeDTO> findAllDeleted() {
        return dopunjavanjeRepository.findByObrisanoTrue()
                .stream().map(Dopunjavanje::toDto).collect(Collectors.toList());
    }

    public Optional<DopunjavanjeDTO> findById(Long id) {
        return dopunjavanjeRepository.findById(id).map(Dopunjavanje::toDto);
    }

    public Optional<Dopunjavanje> findEntityById(Long id) {
        return dopunjavanjeRepository.findById(id);
    }

    public DopunjavanjeDTO save(DopunjavanjeSaveDTO dto) {
        Dopunjavanje e = dto.toEntity();

        Udzbenik udzbenik = udzbenikService.findEntityById(dto.getUdzbenik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Udzbenik with id:" + dto.getUdzbenik_id() + " not found"));

        e.setUdzbenik(udzbenik);

        return dopunjavanjeRepository.save(e).toDto();
    }

    public void delete(Long id) {
        dopunjavanjeRepository.findById(id).ifPresent(e -> {
            e.setObrisano(true);
            dopunjavanjeRepository.save(e);
        });
    }

    public void vrati(Long id) {
        dopunjavanjeRepository.findById(id).ifPresent(e -> {
            e.setObrisano(false);
            dopunjavanjeRepository.save(e);
        });
    }
}
