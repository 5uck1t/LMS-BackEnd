package com.example.demo.service;

import com.example.demo.dto.PravoPristupaDTO;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.PravoPristupa;
import com.example.demo.repository.PravoPristupaRepository;
import com.example.demo.saveDto.PravoPristupaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PravoPristupaService {

    @Autowired
    private PravoPristupaRepository pravoPristupaRepository;

    public List<PravoPristupaDTO> findAll() {
        return ((List<PravoPristupa>) pravoPristupaRepository.findAll())
                .stream()
                .map(PravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public List<PravoPristupaDTO> findAllActive() {

        return ((List<PravoPristupa>) pravoPristupaRepository.findByObrisanoFalse())
                .stream()
                .map(PravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public List<PravoPristupaDTO> findAllDeleted() {

        return ((List<PravoPristupa>) pravoPristupaRepository.findByObrisanoTrue())
                .stream()
                .map(PravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PravoPristupaDTO> findById(Long id) {
        return pravoPristupaRepository.findById(id).map(PravoPristupa::toDto);
    }

    public Optional<PravoPristupa> findEntityById(Long id) {
        return pravoPristupaRepository.findById(id);
    }

    public PravoPristupaDTO save(PravoPristupaSaveDTO pravoPristupa) {

        return pravoPristupaRepository.save(pravoPristupa.toEntity()).toDto();
    }

    public void delete(PravoPristupaDTO pravoPristupa) {
        pravoPristupa.setObrisano(true);
        pravoPristupaRepository.save(pravoPristupa.toEntity());
    }

    public void delete(Long id) {
        Optional<PravoPristupa> optional = pravoPristupaRepository.findById(id);
        if (optional.isPresent()) {
            PravoPristupa pravoPristupa = optional.get();
            pravoPristupa.setObrisano(true);
            pravoPristupaRepository.save(pravoPristupa);
        }
    }

    public void vrati(PravoPristupaDTO adresa) {
        adresa.setObrisano(false);
        pravoPristupaRepository.save(adresa.toEntity());
    }

    public void vrati(Long id) {
        Optional<PravoPristupa> optional = pravoPristupaRepository.findById(id);
        if (optional.isPresent()) {
            PravoPristupa pravoPristupa = optional.get();
            pravoPristupa.setObrisano(false);
            pravoPristupaRepository.save(pravoPristupa);
        }
    }
}
