package com.example.demo.service;

import com.example.demo.dto.OsobaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Osoba;
import com.example.demo.repository.OsobaRepository;
import com.example.demo.saveDto.OsobaSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OsobaService {

    @Autowired
    private OsobaRepository osobaRepository;

    @Autowired
    private AdresaService adresaService;

    @Autowired
    @Lazy
    private NastavnikService nastavnikService;

    @Autowired
    @Lazy
    private StudentService studentService;

    public List<OsobaDTO> findAll() {
        return ((List<Osoba>) osobaRepository.findAll())
                .stream()
                .map(Osoba::toDto)
                .collect(Collectors.toList());
    }

    public List<OsobaDTO> findAllActive() {
        return ((List<Osoba>) osobaRepository.findByObrisanoFalse())
                .stream()
                .map(Osoba::toDto)
                .collect(Collectors.toList());
    }

    public List<OsobaDTO> findAllDeleted() {
        return ((List<Osoba>) osobaRepository.findByObrisanoTrue())
                .stream()
                .map(Osoba::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OsobaDTO> findById(Long id) {
        return osobaRepository.findById(id).map(Osoba::toDto);
    }

    public Optional<Osoba> findEntityById(Long id) {
        return osobaRepository.findById(id);
    }

    public OsobaDTO save(OsobaSaveDTO osoba) {

        Osoba nova = osoba.toEntity();

        nova.setAdresa(adresaService.findEntityById(osoba.getAdresa_id())
                .orElseThrow(() -> new ResourceNotFoundException("Adresa with id:" + osoba.getAdresa_id() + " not found")));

        if(osoba.getNastavnik_id() == null){
            if (osoba.getStudent_id() == null){
                throw new IllegalArgumentException("Osoba mora da bude ili Nastavnik ili Student");
            }
            nova.setStudent(studentService.findEntityById(osoba.getStudent_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Student with id:" + osoba.getStudent_id() + " not found")));
        }

        nova.setNastavnik(nastavnikService.findEntityById(osoba.getNastavnik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik with id:" + osoba.getNastavnik_id() + " not found")));

        return osobaRepository.save(nova).toDto();
    }

    public void delete(OsobaDTO osoba) {
        osoba.setObrisano(true);
        osobaRepository.save(osoba.toEntity());
    }

    public void delete(Long id) {
        Optional<Osoba> optional = osobaRepository.findById(id);
        if (optional.isPresent()) {
            Osoba osoba = optional.get();
            osoba.setObrisano(true);
            osobaRepository.save(osoba);
        }
    }

    public void vrati(OsobaDTO osoba) {
        osoba.setObrisano(false);
        osobaRepository.save(osoba.toEntity());
    }

    public void vrati(Long id) {
        Optional<Osoba> optional = osobaRepository.findById(id);
        if (optional.isPresent()) {
            Osoba osoba = optional.get();
            osoba.setObrisano(false);
            osobaRepository.save(osoba);
        }
    }
}