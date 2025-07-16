package com.example.demo.service;

import com.example.demo.dto.DodeljenoPravoPristupaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Adresa;
import com.example.demo.model.DatumPredmeta;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.repository.DodeljenoPravoPristupaRepository;
import com.example.demo.saveDto.DodeljenoPravoPristupaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DodeljenoPravoPristupaService {

    @Autowired
    private DodeljenoPravoPristupaRepository dodeljenoPravoPristupaRepository;

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;

    @Autowired
    private PravoPristupaService pravoPristupaService;

    public List<DodeljenoPravoPristupaDTO> findAll() {
        return ((List<DodeljenoPravoPristupa>) dodeljenoPravoPristupaRepository.findAll())
                .stream()
                .map(DodeljenoPravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public List<DodeljenoPravoPristupa> findByUlogovaniKorisnikUsername(String username) {
        return ((List<DodeljenoPravoPristupa>) dodeljenoPravoPristupaRepository.findDodeljenoPravoPristupaByUlogovaniKorisnik_UsernameAndObrisanoFalse(username));
    }

    public List<DodeljenoPravoPristupaDTO> findByUlogovaniKorisnikId(Long id) {
        return ((List<DodeljenoPravoPristupa>) dodeljenoPravoPristupaRepository.findDodeljenoPravoPristupaByUlogovaniKorisnik_IdAndObrisanoFalse(id))
                .stream()
                .map(DodeljenoPravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public List<DodeljenoPravoPristupaDTO> findAllActive() {
        return ((List<DodeljenoPravoPristupa>) dodeljenoPravoPristupaRepository.findByObrisanoFalse())
                .stream()
                .map(DodeljenoPravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public List<DodeljenoPravoPristupaDTO> findAllDeleted() {

        return ((List<DodeljenoPravoPristupa>) dodeljenoPravoPristupaRepository.findByObrisanoTrue())
                .stream()
                .map(DodeljenoPravoPristupa::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DodeljenoPravoPristupaDTO> findById(Long id) {
        return dodeljenoPravoPristupaRepository.findById(id).map(DodeljenoPravoPristupa::toDto);
    }

    public Optional<DodeljenoPravoPristupa> findEntityById(Long id) {
        return dodeljenoPravoPristupaRepository.findById(id);
    }

    public DodeljenoPravoPristupaDTO save(DodeljenoPravoPristupaSaveDTO dodeljenoPravoPristupa) {

        DodeljenoPravoPristupa novo = dodeljenoPravoPristupa.toEntity();
        novo.setPravoPristupa(pravoPristupaService.findEntityById(dodeljenoPravoPristupa.getPravoPristupa_id())
                .orElseThrow(() -> new ResourceNotFoundException("Pravo pristupa with id" + dodeljenoPravoPristupa.getPravoPristupa_id() + " not found")));

        novo.setUlogovaniKorisnik(ulogovaniKorisnikService.findEntityById(dodeljenoPravoPristupa.getUlogovaniKorisnik_id())
                .orElseThrow(() -> new EntityNotFoundException("Ulogovani korisnik with id:" + dodeljenoPravoPristupa.getUlogovaniKorisnik_id() + " not found")));
        return dodeljenoPravoPristupaRepository.save(novo).toDto();
    }

    public void delete(DodeljenoPravoPristupaDTO dodeljenoPravoPristupa) {
        dodeljenoPravoPristupa.setObrisano(true);
        dodeljenoPravoPristupaRepository.save(dodeljenoPravoPristupa.toEntity());
    }

    public void delete(Long id) {
        Optional<DodeljenoPravoPristupa> optional = dodeljenoPravoPristupaRepository.findById(id);
        if (optional.isPresent()) {
            DodeljenoPravoPristupa dodeljenoPravoPristupa = optional.get();
            dodeljenoPravoPristupa.setObrisano(true);
            dodeljenoPravoPristupaRepository.save(dodeljenoPravoPristupa);
        }
    }

    public void vrati(DodeljenoPravoPristupaDTO dodeljenoPravoPristupa) {
        dodeljenoPravoPristupa.setObrisano(false);
        dodeljenoPravoPristupaRepository.save(dodeljenoPravoPristupa.toEntity());
    }

    public void vrati(Long id) {
        Optional<DodeljenoPravoPristupa> optional = dodeljenoPravoPristupaRepository.findById(id);
        if (optional.isPresent()) {
            DodeljenoPravoPristupa dodeljenoPravoPristupa = optional.get();
            dodeljenoPravoPristupa.setObrisano(false);
            dodeljenoPravoPristupaRepository.save(dodeljenoPravoPristupa);
        }
    }
}