package com.example.demo.service;

import com.example.demo.dto.StranicaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Stranica;
import com.example.demo.repository.StranicaRepository;
import com.example.demo.saveDto.StranicaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StranicaService {

    @Autowired
    private StranicaRepository stranicaRepository;

    @Autowired
    private UdzbenikService udzbenikService;

    public List<StranicaDTO> findAll() {
        return ((List<Stranica>)stranicaRepository.findAll())
                .stream()
                .map(Stranica::toDto)
                .collect(Collectors.toList());
    }

    public List<StranicaDTO> findAllActive() {
        return stranicaRepository.findByObrisanoFalse()
                .stream()
                .map(Stranica::toDto)
                .collect(Collectors.toList());
    }

    public List<StranicaDTO> findAllDeleted() {
        return stranicaRepository.findByObrisanoTrue()
                .stream()
                .map(Stranica::toDto)
                .collect(Collectors.toList());
    }

    public List<StranicaDTO> findByUdzbenikId(Long udzbenikId) {
        return stranicaRepository.findByUdzbenikId(udzbenikId)
                .stream()
                .map(Stranica::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StranicaDTO> findById(Long id) {
        return stranicaRepository.findById(id).map(Stranica::toDto);
    }

    public Optional<Stranica> findEntityById(Long id) {
        return stranicaRepository.findById(id);
    }

    public StranicaDTO save(StranicaSaveDTO stranicaDTO) {
        Stranica stranica = stranicaDTO.toEntity();

        stranica.setUdzbenik(udzbenikService.findEntityById(stranicaDTO.getUdzbenik_id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Udzbenik with id: " + stranicaDTO.getUdzbenik_id() + " not found")));

        return stranicaRepository.save(stranica).toDto();
    }

    public void delete(Long id) {
        Optional<Stranica> optional = stranicaRepository.findById(id);
        if (optional.isPresent()) {
            Stranica stranica = optional.get();
            stranica.setObrisano(true);
            stranicaRepository.save(stranica);
        }
    }

    public void vrati(Long id) {
        Optional<Stranica> optional = stranicaRepository.findById(id);
        if (optional.isPresent()) {
            Stranica stranica = optional.get();
            stranica.setObrisano(false);
            stranicaRepository.save(stranica);
        }
    }
}
