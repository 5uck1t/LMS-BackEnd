package com.example.demo.service;

import com.example.demo.dto.TipZvanjaDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.TipZvanja;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.repository.UlogovaniKorisnikRepository;
import com.example.demo.saveDto.UlogovaniKorisnikSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UlogovaniKorisnikService {

    @Autowired
    private UlogovaniKorisnikRepository ulogovaniKorisnikRepository;

    @Autowired
    private OsobaService osobaService;

    public List<UlogovaniKorisnikDTO> findAll() {
        return ((List<UlogovaniKorisnik>) ulogovaniKorisnikRepository.findAll())
                .stream()
                .map(UlogovaniKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public List<UlogovaniKorisnikDTO> findAllActive() {
        return ((List<UlogovaniKorisnik>) ulogovaniKorisnikRepository.findByObrisanoFalse())
                .stream()
                .map(UlogovaniKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public List<UlogovaniKorisnikDTO> findAllDeleted() {
        return ((List<UlogovaniKorisnik>) ulogovaniKorisnikRepository.findByObrisanoTrue())
                .stream()
                .map(UlogovaniKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UlogovaniKorisnikDTO> findById(Long id) {
        return ulogovaniKorisnikRepository.findById(id).map(UlogovaniKorisnik::toDto);
    }

    public Optional<UlogovaniKorisnik> findEntityById(Long id) {
        return ulogovaniKorisnikRepository.findById(id);
    }

    public UlogovaniKorisnikDTO save(UlogovaniKorisnikSaveDTO ulogovaniKorisnik) {
        UlogovaniKorisnik novi = ulogovaniKorisnik.toEntity();

        novi.setOsoba(osobaService.findEntityById(ulogovaniKorisnik.getOsoba_id())
                .orElseThrow(() -> new EntityNotFoundException("Osoba with id:" + ulogovaniKorisnik.getOsoba_id() + " not found")));
        return ulogovaniKorisnikRepository.save(novi).toDto();
    }

    public void delete(UlogovaniKorisnikDTO ulogovaniKorisnik) {
        ulogovaniKorisnik.setObrisano(true);
        ulogovaniKorisnikRepository.save(ulogovaniKorisnik.toEntity());
    }

    public void delete(Long id) {
        Optional<UlogovaniKorisnik> optional = ulogovaniKorisnikRepository.findById(id);
        if (optional.isPresent()) {
            UlogovaniKorisnik ulogovaniKorisnik = optional.get();
            ulogovaniKorisnik.setObrisano(true);
            ulogovaniKorisnikRepository.save(ulogovaniKorisnik);
        }
    }

    public void vrati(UlogovaniKorisnikDTO ulogovaniKorisnik) {
        ulogovaniKorisnik.setObrisano(false);
        ulogovaniKorisnikRepository.save(ulogovaniKorisnik.toEntity());
    }

    public void vrati(Long id) {
        Optional<UlogovaniKorisnik> optional = ulogovaniKorisnikRepository.findById(id);
        if (optional.isPresent()) {
            UlogovaniKorisnik ulogovaniKorisnik = optional.get();
            ulogovaniKorisnik.setObrisano(false);
            ulogovaniKorisnikRepository.save(ulogovaniKorisnik);
        }
    }

    public UlogovaniKorisnikDTO findByUsername(String username){
        return ulogovaniKorisnikRepository.findUlogovaniKorisnikByUsername(username).map(UlogovaniKorisnik::toDto).orElse(null);
    }

    public UlogovaniKorisnikDTO findByUsernameAndPassword(String username, String password){
        return ulogovaniKorisnikRepository.findUlogovaniKorisnikByUsernameAndPassword(username, password).map(UlogovaniKorisnik::toDto).orElse(null);
    }
}