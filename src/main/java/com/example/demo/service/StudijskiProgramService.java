package com.example.demo.service;

import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Student;
import com.example.demo.model.StudijskiProgram;
import com.example.demo.repository.StudijskiProgramRepository;
import com.example.demo.saveDto.StudijskiProgramSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudijskiProgramService {

    @Autowired
    private StudijskiProgramRepository studijskiProgramRepository;

    @Autowired
    private KatedraService katedraService;

    public List<StudijskiProgramDTO> findAll() {
        return ((List<StudijskiProgram>) studijskiProgramRepository.findAll())
                .stream()
                .map(StudijskiProgram::toDto)
                .collect(Collectors.toList());
    }

    public List<StudijskiProgramDTO> findAllActive() {
        return ((List<StudijskiProgram>) studijskiProgramRepository.findByObrisanoFalse())
                .stream()
                .map(StudijskiProgram::toDto)
                .collect(Collectors.toList());
    }

    public List<StudijskiProgramDTO> findAllDeleted() {
        return ((List<StudijskiProgram>) studijskiProgramRepository.findByObrisanoTrue())
                .stream()
                .map(StudijskiProgram::toDto)
                .collect(Collectors.toList());
    }

    public List<StudijskiProgramDTO> findByKatedraId(Long id) {
        return ((List<StudijskiProgram>) studijskiProgramRepository.findByKatedra_IdAndObrisanoFalse(id))
                .stream()
                .map(StudijskiProgram::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StudijskiProgramDTO> findById(Long id) {
        return studijskiProgramRepository.findById(id).map(StudijskiProgram::toDto);
    }

    public Optional<StudijskiProgram> findEntityById(Long id) {
        return studijskiProgramRepository.findById(id);
    }

    public StudijskiProgramDTO save(StudijskiProgramSaveDTO studijskiProgram) {

        StudijskiProgram novi = studijskiProgram.toEntity();

        novi.setKatedra(katedraService.findEntityById(studijskiProgram.getKatedra_id())
                .orElseThrow(() -> new ResourceNotFoundException("Katedra with id:" + studijskiProgram.getKatedra_id() + " not found")));
        return studijskiProgramRepository.save(novi).toDto();
    }

    public void delete(StudijskiProgramDTO studijskiProgram) {
        studijskiProgram.setObrisano(true);
        studijskiProgramRepository.save(studijskiProgram.toEntity());
    }

    public void delete(Long id) {
        Optional<StudijskiProgram> optional = studijskiProgramRepository.findById(id);
        if (optional.isPresent()) {
            StudijskiProgram studijskiProgram = optional.get();
            studijskiProgram.setObrisano(true);
            studijskiProgramRepository.save(studijskiProgram);
        }
    }

    public void vrati(StudijskiProgramDTO studijskiProgram) {
        studijskiProgram.setObrisano(false);
        studijskiProgramRepository.save(studijskiProgram.toEntity());
    }

    public void vrati(Long id) {
        Optional<StudijskiProgram> optional = studijskiProgramRepository.findById(id);
        if (optional.isPresent()) {
            StudijskiProgram studijskiProgram = optional.get();
            studijskiProgram.setObrisano(false);
            studijskiProgramRepository.save(studijskiProgram);
        }
    }
}