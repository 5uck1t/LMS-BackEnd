package com.example.demo.service;

import com.example.demo.dto.StudentNaPredmetuDTO;
import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Osoba;
import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Predmet;
import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Student;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.repository.RealizacijaPredmetaRepository;
import com.example.demo.saveDto.RealizacijaPredmetaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RealizacijaPredmetaService {

    @Autowired
    private RealizacijaPredmetaRepository realizacijaPredmetaRepository;

    @Autowired
    private NastavnikService nastavnikService;

    @Autowired
    private GodinaStudijaService godinaStudijaService;

    @Autowired
    private PredmetService predmetService;

    public List<RealizacijaPredmetaDTO> findAll() {
        return ((List<RealizacijaPredmeta>) realizacijaPredmetaRepository.findAll())
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<RealizacijaPredmetaDTO> findAllActive() {
        return ((List<RealizacijaPredmeta>) realizacijaPredmetaRepository.findByObrisanoFalse())
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<RealizacijaPredmetaDTO> findAllDeleted() {
        return ((List<RealizacijaPredmeta>) realizacijaPredmetaRepository.findByObrisanoTrue())
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<PredmetDTO> findPredmetiByNastavnikId(Long id) {
        return ((List<Predmet>) realizacijaPredmetaRepository.findPredmetiByNastavnikId(id))
                .stream()
                .map(Predmet::toDto)
                .collect(Collectors.toList());
    }

    public List<RealizacijaPredmetaDTO> findRealizacijaPredmetaByGodinaStudijaId(Long id) {
        return ((List<RealizacijaPredmeta>) realizacijaPredmetaRepository.findRealizacijaPredmetaByGodinaStudijaId(id))
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<RealizacijaPredmetaDTO> findRealizacijaPredmetaByStudijskiProgramId(Long id) {
        return ((List<RealizacijaPredmeta>) realizacijaPredmetaRepository.findRealizacijaPredmetaByStudijskiProgramId(id))
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public Optional<RealizacijaPredmetaDTO> findById(Long id) {
        return realizacijaPredmetaRepository.findById(id).map(RealizacijaPredmeta::toDto);
    }

    public Optional<RealizacijaPredmeta> findEntityById(Long id) {
        return realizacijaPredmetaRepository.findById(id);
    }

    public RealizacijaPredmetaDTO save(RealizacijaPredmetaSaveDTO realizacijaPredmeta) {

        RealizacijaPredmeta nova = realizacijaPredmeta.toEntity();

        nova.setGodinaStudija(godinaStudijaService.findEntityById(realizacijaPredmeta.getGodinaStudija_id())
                .orElseThrow(() -> new ResourceNotFoundException("Godina studija with id:" + realizacijaPredmeta.getGodinaStudija_id() + " not found")));

        nova.setNastavnik(nastavnikService.findEntityById(realizacijaPredmeta.getNastavnik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Nastavnik with id:" + realizacijaPredmeta.getNastavnik_id() + " not found")));

        nova.setPredmet(predmetService.findEntityById(realizacijaPredmeta.getPredmet_id())
                .orElseThrow(() -> new ResourceNotFoundException("Predmet with id:" + realizacijaPredmeta.getPredmet_id() + " not found")));

        return realizacijaPredmetaRepository.save(nova).toDto();
    }

    public void delete(RealizacijaPredmetaDTO realizacijaPredmeta) {
        realizacijaPredmeta.setObrisano(true);
        realizacijaPredmetaRepository.save(realizacijaPredmeta.toEntity());
    }

    public void delete(Long id) {
        Optional<RealizacijaPredmeta> optional = realizacijaPredmetaRepository.findById(id);
        if (optional.isPresent()) {
            RealizacijaPredmeta realizacijaPredmeta = optional.get();
            realizacijaPredmeta.setObrisano(true);
            realizacijaPredmetaRepository.save(realizacijaPredmeta);
        }
    }

    public void vrati(RealizacijaPredmetaDTO realizacijaPredmeta) {
        realizacijaPredmeta.setObrisano(false);
        realizacijaPredmetaRepository.save(realizacijaPredmeta.toEntity());
    }

    public void vrati(Long id) {
        Optional<RealizacijaPredmeta> optional = realizacijaPredmetaRepository.findById(id);
        if (optional.isPresent()) {
            RealizacijaPredmeta realizacijaPredmeta = optional.get();
            realizacijaPredmeta.setObrisano(false);
            realizacijaPredmetaRepository.save(realizacijaPredmeta);
        }
    }
    
    public List<RealizacijaPredmetaDTO> findByNastavnikId(Long nastavnikId) {
        return realizacijaPredmetaRepository.findByNastavnikIdAndObrisanoFalse(nastavnikId)
                .stream()
                .map(RealizacijaPredmeta::toDto)
                .toList();
    }
    
    public List<StudentNaPredmetuDTO> getStudentiZaPredmet(Long predmetId) {
        List<RealizacijaPredmeta> realizacije = realizacijaPredmetaRepository.findByPredmetId(predmetId);

        return realizacije.stream()
            .flatMap(rp -> rp.getPohadjanjaPredmeta().stream())
            .filter(p -> !Boolean.TRUE.equals(p.getObrisano()))
            .map(p -> {
                StudentNaGodini sng = p.getStudentNaGodini();
                Student student = sng.getStudent();
                Osoba osoba = student.getOsoba();

                return new StudentNaPredmetuDTO(
                    student.getId(),
                    osoba.getIme(),
                    osoba.getPrezime(),
                    osoba.getJmbg(),
                    sng.getBrojIndeksa(),
                    sng.getId()
                );
            })
            .toList();
    }


}