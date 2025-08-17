package com.example.demo.service;

import com.example.demo.dto.UniverzitetDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.model.Univerzitet;
import com.example.demo.repository.UniverzitetRepository;
import com.example.demo.saveDto.UniverzitetSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniverzitetService {

    @Autowired
    private UniverzitetRepository univerzitetRepository;

    @Autowired
    private AdresaService adresaService;

    public List<UniverzitetDTO> findAll() {
        return ((List<Univerzitet>) univerzitetRepository.findAll())
                .stream()
                .map(Univerzitet::toDto)
                .collect(Collectors.toList());
    }

    public List<UniverzitetDTO> findAllActive() {
        return ((List<Univerzitet>) univerzitetRepository.findByObrisanoFalse())
                .stream()
                .map(Univerzitet::toDto)
                .collect(Collectors.toList());
    }

    public List<UniverzitetDTO> findAllDeleted() {
        return ((List<Univerzitet>) univerzitetRepository.findByObrisanoTrue())
                .stream()
                .map(Univerzitet::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UniverzitetDTO> findById(Long id) {
        return univerzitetRepository.findById(id).map(Univerzitet::toDto);
    }

    public Optional<Univerzitet> findEntityById(Long id) {
        return univerzitetRepository.findById(id);
    }

    public UniverzitetDTO save(UniverzitetSaveDTO univerzitet) {

        Univerzitet novi = univerzitet.toEntity();

        novi.setAdresa(adresaService.findEntityById(univerzitet.getAdresa_id())
                .orElseThrow(() -> new ResourceNotFoundException("Adresa with id:" + univerzitet.getAdresa_id() + " not found")));

        return univerzitetRepository.save(novi).toDto();
    }

    public void delete(UniverzitetDTO univerzitet) {
        univerzitet.setObrisano(true);
        univerzitetRepository.save(univerzitet.toEntity());
    }

    public void delete(Long id) {
        Optional<Univerzitet> optional = univerzitetRepository.findById(id);
        if (optional.isPresent()) {
            Univerzitet univerzitet = optional.get();
            univerzitet.setObrisano(true);
            univerzitetRepository.save(univerzitet);
        }
    }

    public void vrati(UniverzitetDTO univerzitet) {
        univerzitet.setObrisano(false);
        univerzitetRepository.save(univerzitet.toEntity());
    }

    public void vrati(Long id) {
        Optional<Univerzitet> optional = univerzitetRepository.findById(id);
        if (optional.isPresent()) {
            Univerzitet univerzitet = optional.get();
            univerzitet.setObrisano(false);
            univerzitetRepository.save(univerzitet);
        }
    }
}