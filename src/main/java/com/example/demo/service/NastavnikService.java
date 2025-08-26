package com.example.demo.service;

import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.NastavnikForumDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Nastavnik;
import com.example.demo.model.NastavnikHasZvanje;
import com.example.demo.model.Osoba;
import com.example.demo.repository.NastavnikRepository;
import com.example.demo.repository.UlogovaniKorisnikRepository;
import com.example.demo.saveDto.NastavnikSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NastavnikService {

    @Autowired
    private NastavnikRepository nastavnikRepository;
    
    @Autowired
    private UlogovaniKorisnikRepository ulogovaniKorisnikRepository;

    @Autowired
    private OsobaService osobaService;

    @Autowired
    private FakultetService fakultetService;

    public List<NastavnikDTO> findAll() {
        return ((List<Nastavnik>) nastavnikRepository.findAll())
                .stream()
                .map(Nastavnik::toDto)
                .collect(Collectors.toList());
    }

    public List<NastavnikDTO> findAllActive() {
        return ((List<Nastavnik>) nastavnikRepository.findByObrisanoFalse())
                .stream()
                .map(Nastavnik::toDto)
                .collect(Collectors.toList());    }

    public List<NastavnikDTO> findAllDeleted() {
        return ((List<Nastavnik>) nastavnikRepository.findByObrisanoTrue())
                .stream()
                .map(Nastavnik::toDto)
                .collect(Collectors.toList());    }

    public Optional<NastavnikDTO> findById(Long id) {
        return nastavnikRepository.findById(id).map(Nastavnik::toDto);
    }

    public Optional<NastavnikDTO> findByOsobaId(Long id) {
        return nastavnikRepository.findByOsoba_Id(id).map(Nastavnik::toDto);
    }

    public Optional<Nastavnik> findEntityById(Long id) {
        return nastavnikRepository.findById(id);
    }

    public NastavnikDTO save(NastavnikSaveDTO nastavnik) {

        Nastavnik novi = nastavnik.toEntity();

        novi.setOsoba(osobaService.findEntityById(nastavnik.getOsoba_id())
                .orElseThrow(() -> new ResourceNotFoundException("Osoba with id:" + nastavnik.getOsoba_id() + " not found")));

        return nastavnikRepository.save(novi).toDto();
    }

    public void delete(NastavnikDTO nastavnik) {
        nastavnik.setObrisano(true);
        nastavnikRepository.save(nastavnik.toEntity());
    }

    public void delete(Long id) {
        Optional<Nastavnik> optional = nastavnikRepository.findById(id);
        if (optional.isPresent()) {
            Nastavnik nastavnik = optional.get();
            nastavnik.setObrisano(true);
            nastavnikRepository.save(nastavnik);
        }
    }

    public void vrati(NastavnikDTO nastavnik) {
        nastavnik.setObrisano(false);
        nastavnikRepository.save(nastavnik.toEntity());
    }

    public void vrati(Long id) {
        Optional<Nastavnik> optional = nastavnikRepository.findById(id);
        if (optional.isPresent()) {
            Nastavnik nastavnik = optional.get();
            nastavnik.setObrisano(false);
            nastavnikRepository.save(nastavnik);
        }
    }
    
    public Optional<Long> findNastavnikIdByUserId(Long userId) {
        return nastavnikRepository.findNastavnikIdByUserId(userId);
    }
    
    
    public Optional<Nastavnik> findByOsoba(Osoba osoba) {
        return nastavnikRepository.findByOsoba(osoba);
    }
    
    public List<NastavnikForumDTO> getAvailableNastavnici(Long forumId, String filter) {
        List<Nastavnik> nastavnici;
        if (filter == null || filter.isBlank()) {
            nastavnici = nastavnikRepository.findNastavniciNotInForum(forumId);
        } else {
            nastavnici = nastavnikRepository.searchNastavniciNotInForum(forumId, filter);
        }

        return nastavnici.stream().map(n -> {
            NastavnikForumDTO dto = new NastavnikForumDTO();
            dto.setNastavnikId(n.getId());
            dto.setIme(n.getOsoba().getIme());
            dto.setPrezime(n.getOsoba().getPrezime());
            dto.setJmbg(n.getOsoba().getJmbg());

            Long ukId = ulogovaniKorisnikRepository.findUlogovaniKorisnikIdByOsobaId(n.getOsoba().getId());
            dto.setUlogovaniKorisnikId(ukId);

            return dto;
        }).toList();
    }


}