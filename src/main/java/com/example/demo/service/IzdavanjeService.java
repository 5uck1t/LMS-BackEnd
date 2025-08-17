package com.example.demo.service;

import com.example.demo.dto.IzdavanjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Izdavanje;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.model.Udzbenik;
import com.example.demo.repository.IzdavanjeRepository;
import com.example.demo.saveDto.IzdavanjeSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IzdavanjeService {

    @Autowired
    private IzdavanjeRepository izdavanjeRepository;

    @Autowired
    private UdzbenikService udzbenikService;

    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    public List<IzdavanjeDTO> findAll() {
        return StreamSupport.stream(izdavanjeRepository.findAll().spliterator(), false)
                .map(Izdavanje::toDto)
                .collect(Collectors.toList());
    }

    public List<IzdavanjeDTO> findAllActive() {
        return izdavanjeRepository.findByObrisanoFalse()
                .stream().map(Izdavanje::toDto).collect(Collectors.toList());
    }

    public List<IzdavanjeDTO> findAllDeleted() {
        return izdavanjeRepository.findByObrisanoTrue()
                .stream().map(Izdavanje::toDto).collect(Collectors.toList());
    }

    public Optional<IzdavanjeDTO> findById(Long id) {
        return izdavanjeRepository.findById(id).map(Izdavanje::toDto);
    }

    public Optional<Izdavanje> findEntityById(Long id) {
        return izdavanjeRepository.findById(id);
    }

    public IzdavanjeDTO save(IzdavanjeSaveDTO dto) {
        Izdavanje e = dto.toEntity();

        Udzbenik udzbenik = udzbenikService.findEntityById(dto.getUdzbenik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Udzbenik with id:" + dto.getUdzbenik_id() + " not found"));
        StudentNaGodini student = studentNaGodiniService.findEntityById(dto.getStudentNaGodini_id())
                .orElseThrow(() -> new ResourceNotFoundException("StudentNaGodini with id:" + dto.getStudentNaGodini_id() + " not found"));
        udzbenik.setKolicina(udzbenik.getKolicina()-1);
        e.setUdzbenik(udzbenik);
        e.setStudent(student);

        return izdavanjeRepository.save(e).toDto();
    }

    public void delete(Long id) {
        izdavanjeRepository.findById(id).ifPresent(e -> {
            e.setObrisano(true);
            izdavanjeRepository.save(e);
        });
    }

    public void vrati(Long id) {
        izdavanjeRepository.findById(id).ifPresent(e -> {
            e.setObrisano(false);
            izdavanjeRepository.save(e);
        });
    }
}
