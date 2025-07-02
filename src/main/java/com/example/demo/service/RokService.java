package com.example.demo.service;

import com.example.demo.dto.RokDTO;
import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Rok;
import com.example.demo.repository.RokRepository;
import com.example.demo.saveDto.RokSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RokService {

    @Autowired
    private RokRepository rokRepository;

    public List<RokDTO> findAll() {
        return ((List<Rok>) rokRepository.findAll())
                .stream()
                .map(Rok::toDto)
                .collect(Collectors.toList());
    }

    public List<RokDTO> findAllActive() {
        return ((List<Rok>) rokRepository.findByObrisanoFalse())
                .stream()
                .map(Rok::toDto)
                .collect(Collectors.toList());
    }

    public List<RokDTO> findAllDeleted() {
        return ((List<Rok>) rokRepository.findByObrisanoTrue())
                .stream()
                .map(Rok::toDto)
                .collect(Collectors.toList());    }

    public Optional<RokDTO> findById(Long id) {
        return rokRepository.findById(id).map(Rok::toDto);
    }

    public RokDTO save(RokSaveDTO rok) {

        LocalDate datumPocetka = rok.getPocetak().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate datumKraja = rok.getKraj().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate danas = LocalDate.now();

        // Kraj ne sme biti pre početka
        if (datumKraja.isBefore(datumPocetka)) {
            throw new IllegalArgumentException("Kraj ne sme biti pre početka");
        }

        // Početak ne sme biti pre današnjeg dana
        if (datumPocetka.isBefore(danas)) {
            throw new IllegalArgumentException("Pocetak ne sme biti pre današnjeg dana");
        }

        return rokRepository.save(rok.toEntity()).toDto();
    }

    public void delete(RokDTO rok) {
        rok.setObrisano(true);
        rokRepository.save(rok.toEntity());
    }

    public void delete(Long id) {
        Optional<Rok> optional = rokRepository.findById(id);
        if (optional.isPresent()) {
            Rok rok = optional.get();
            rok.setObrisano(true);
            rokRepository.save(rok);
        }
    }

    public void vrati(RokDTO rok) {
        rok.setObrisano(false);
        rokRepository.save(rok.toEntity());
    }

    public void vrati(Long id) {
        Optional<Rok> optional = rokRepository.findById(id);
        if (optional.isPresent()) {
            Rok rok = optional.get();
            rok.setObrisano(false);
            rokRepository.save(rok);
        }
    }
}