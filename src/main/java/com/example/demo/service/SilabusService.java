package com.example.demo.service;

import com.example.demo.dto.SilabusDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.Osoba;
import com.example.demo.model.Predmet;
import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Rok;
import com.example.demo.model.Silabus;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.repository.NastavnikRepository;
import com.example.demo.repository.RealizacijaPredmetaRepository;
import com.example.demo.repository.SilabusRepository;
import com.example.demo.repository.UlogovaniKorisnikRepository;
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
    
    @Autowired
    private UlogovaniKorisnikRepository korisnikRepo;

    @Autowired
    private NastavnikRepository nastavnikRepo;

    @Autowired
    private RealizacijaPredmetaRepository realizacijaRepo;

    public List<SilabusDTO> findByNastavnikUsername(String username) {
        // 1. Nađi ulogovanog korisnika
        UlogovaniKorisnik korisnik = korisnikRepo.findUlogovaniKorisnikByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Korisnik ne postoji"));

        // 2. Nađi nastavnika vezanog za tog korisnika
        Osoba osoba = korisnik.getOsoba();
        Nastavnik nastavnik = nastavnikRepo.findByOsoba(osoba)
            .orElseThrow(() -> new ResourceNotFoundException("Nastavnik ne postoji"));

        // 3. Nađi sve predmete koje predaje
        List<RealizacijaPredmeta> realizacije = realizacijaRepo.findByNastavnik(nastavnik);

        // 4. Pronađi sve silabuse vezane za te predmete
        List<Predmet> predmeti = realizacije.stream()
            .map(RealizacijaPredmeta::getPredmet)
            .toList();

        return silabusRepository.findByPredmetInAndObrisanoFalse(predmeti)
            .stream()
            .map(Silabus::toDto)
            .toList();
    }
    public List<SilabusDTO> findByPredmetId(Long predmetId) {
        return silabusRepository.findByPredmetId(predmetId)
                .stream()
                .map(Silabus::toDto)
                .collect(Collectors.toList());
    }

}