package com.example.demo.service;

import com.example.demo.dto.ObavestenjeDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.NaucnaOblast;
import com.example.demo.model.Obavestenje;
import com.example.demo.repository.ObavestenjeRepository;
import com.example.demo.saveDto.ObavestenjeSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObavestenjeService {

    @Autowired
    private ObavestenjeRepository obavestenjeRepository;

    @Autowired
    private ForumService forumService;

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;

    public List<ObavestenjeDTO> findAll() {
        return ((List<Obavestenje>) obavestenjeRepository.findAll())
                .stream()
                .map(Obavestenje::toDto)
                .collect(Collectors.toList());    }

    public List<ObavestenjeDTO> findAllActive() {
        return ((List<Obavestenje>) obavestenjeRepository.findByObrisanoFalse())
                .stream()
                .map(Obavestenje::toDto)
                .collect(Collectors.toList());
    }

    public List<ObavestenjeDTO> findByForumId(Long id) {
        return ((List<Obavestenje>) obavestenjeRepository.findByForum_Id(id))
                .stream()
                .map(Obavestenje::toDto)
                .collect(Collectors.toList());
    }

    public List<ObavestenjeDTO> findByForumNaziv(String forumNaziv) {
        return ((List<Obavestenje>) obavestenjeRepository.findByForum_Naziv(forumNaziv))
                .stream()
                .map(Obavestenje::toDto)
                .collect(Collectors.toList());
    }

    public List<ObavestenjeDTO> findAllDeleted() {
        return ((List<Obavestenje>) obavestenjeRepository.findByObrisanoTrue())
                .stream()
                .map(Obavestenje::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ObavestenjeDTO> findById(Long id) {
        return obavestenjeRepository.findById(id).map(Obavestenje::toDto);
    }

    public Optional<Obavestenje> findEntityById(Long id) {
        return obavestenjeRepository.findById(id);
    }

    public ObavestenjeDTO save(ObavestenjeSaveDTO obavestenje) {

        Obavestenje novo = obavestenje.toEntity();
        
        if (obavestenje.getUlogovaniKorisnik_id() == null)
            throw new IllegalArgumentException("ulogovaniKorisnik_id ne sme biti null");

        if (obavestenje.getForum_id() == null)
            throw new IllegalArgumentException("forum_id ne sme biti null");

        novo.setForum(forumService.findEntityById(obavestenje.getForum_id())
                .orElseThrow(() -> new ResourceNotFoundException("Forum with id:" + obavestenje.getForum_id() + " not found")));

        novo.setKorisnik(ulogovaniKorisnikService.findEntityById(obavestenje.getUlogovaniKorisnik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik with id:" + obavestenje.getUlogovaniKorisnik_id() + " not found")));

        return obavestenjeRepository.save(novo).toDto();
    }

    public void delete(ObavestenjeDTO obavestenje) {
        obavestenje.setObrisano(true);
        obavestenjeRepository.save(obavestenje.toEntity());
    }

    public void delete(Long id) {
        Optional<Obavestenje> optional = obavestenjeRepository.findById(id);
        if (optional.isPresent()) {
            Obavestenje obavestenje = optional.get();
            obavestenje.setObrisano(true);
            obavestenjeRepository.save(obavestenje);
        }
    }

    public void vrati(ObavestenjeDTO obavestenje) {
        obavestenje.setObrisano(false);
        obavestenjeRepository.save(obavestenje.toEntity());
    }

    public void vrati(Long id) {
        Optional<Obavestenje> optional = obavestenjeRepository.findById(id);
        if (optional.isPresent()) {
            Obavestenje obavestenje = optional.get();
            obavestenje.setObrisano(false);
            obavestenjeRepository.save(obavestenje);
        }
    }
}