package com.example.demo.service;

import com.example.demo.dto.SilabusDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Rok;
import com.example.demo.model.Silabus;
import com.example.demo.repository.SilabusRepository;
import com.example.demo.saveDto.SilabusSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SilabusService {

    @Autowired
    private SilabusRepository silabusRepository;

    @Autowired
    private PredmetService predmetService;

    public List<SilabusDTO> findAll() {
        return ((List<Silabus>) silabusRepository.findAll())
                .stream()
                .map(Silabus::toDto)
                .collect(Collectors.toList());
    }

    public List<SilabusDTO> findAllActive() {
        return ((List<Silabus>) silabusRepository.findByObrisanoFalse())
                .stream()
                .map(Silabus::toDto)
                .collect(Collectors.toList());
    }

    public List<SilabusDTO> findAllDeleted() {
        return ((List<Silabus>) silabusRepository.findByObrisanoTrue())
                .stream()
                .map(Silabus::toDto)
                .collect(Collectors.toList());
    }

    public Optional<SilabusDTO> findById(Long id) {
        return silabusRepository.findById(id).map(Silabus::toDto);
    }

    public Optional<Silabus> findEntityById(Long id) {
        return silabusRepository.findById(id);
    }

    public SilabusDTO save(SilabusSaveDTO silabus) {

        Silabus novi = silabus.toEntity();

        novi.setPredmet(predmetService.findEntityById(silabus.getPredmet_id())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet with id:" + silabus.getPredmet_id() + " not found")));

        return silabusRepository.save(novi).toDto();
    }

    public void delete(SilabusDTO silabus) {
        silabus.setObrisano(true);
        silabusRepository.save(silabus.toEntity());
    }

    public void delete(Long id) {
        Optional<Silabus> optional = silabusRepository.findById(id);
        if (optional.isPresent()) {
            Silabus silabus = optional.get();
            silabus.setObrisano(true);
            silabusRepository.save(silabus);
        }
    }

    public void vrati(SilabusDTO silabus) {
        silabus.setObrisano(false);
        silabusRepository.save(silabus.toEntity());
    }

    public void vrati(Long id) {
        Optional<Silabus> optional = silabusRepository.findById(id);
        if (optional.isPresent()) {
            Silabus silabus = optional.get();
            silabus.setObrisano(false);
            silabusRepository.save(silabus);
        }
    }
}