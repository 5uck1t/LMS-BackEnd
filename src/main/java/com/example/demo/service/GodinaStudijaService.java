package com.example.demo.service;

import com.example.demo.dto.GodinaStudijaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Forum;
import com.example.demo.model.GodinaStudija;
import com.example.demo.repository.GodinaStudijaRepository;
import com.example.demo.saveDto.GodinaStudijaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GodinaStudijaService {

    @Autowired
    private GodinaStudijaRepository godinaStudijaRepository;

    @Autowired
    private StudijskiProgramService studijskiProgramService;

    public List<GodinaStudijaDTO> findAll() {
        return ((List<GodinaStudija>) godinaStudijaRepository.findAll())
                .stream()
                .map(GodinaStudija::toDto)
                .collect(Collectors.toList());
    }

    public List<GodinaStudijaDTO> findAllActive() {

        return ((List<GodinaStudija>) godinaStudijaRepository.findByObrisanoFalse())
                .stream()
                .map(GodinaStudija::toDto)
                .collect(Collectors.toList());
    }

    public List<GodinaStudijaDTO> findAllDeleted() {
        return ((List<GodinaStudija>) godinaStudijaRepository.findByObrisanoTrue())
                .stream()
                .map(GodinaStudija::toDto)
                .collect(Collectors.toList());
    }

    public Optional<GodinaStudijaDTO> findById(Long id) {
        return godinaStudijaRepository.findById(id).map(GodinaStudija::toDto);
    }

    public Optional<GodinaStudija> findEntityById(Long id) {
        return godinaStudijaRepository.findById(id);
    }

    public GodinaStudijaDTO save(GodinaStudijaSaveDTO godinaStudija) {

        int trenutnaGodina = Year.now().getValue();  // npr. 2025
        int pocetnaGodina = Integer.parseInt(godinaStudija.getGodina().split("/")[0]);

        if (pocetnaGodina < trenutnaGodina) {
            throw new IllegalArgumentException("Godina studija ne moze biti u proslosti");
        }

        GodinaStudija nova = godinaStudija.toEntity();

        nova.setStudijskiProgram(studijskiProgramService.findEntityById(godinaStudija.getStudijskiProgram_id())
                .orElseThrow(() -> new ResourceNotFoundException("Studijski program with id:" + godinaStudija.getStudijskiProgram_id() + " not found")));

        return godinaStudijaRepository.save(nova).toDto();
    }

    public void delete(GodinaStudijaDTO godinaStudija) {
        godinaStudija.setObrisano(true);
        godinaStudijaRepository.save(godinaStudija.toEntity());
    }

    public void delete(Long id) {
        Optional<GodinaStudija> optional = godinaStudijaRepository.findById(id);
        if (optional.isPresent()) {
            GodinaStudija godinaStudija = optional.get();
            godinaStudija.setObrisano(true);
            godinaStudijaRepository.save(godinaStudija);
        }
    }

    public void vrati(GodinaStudijaDTO godinaStudija) {
        godinaStudija.setObrisano(false);
        godinaStudijaRepository.save(godinaStudija.toEntity());
    }

    public void vrati(Long id) {
        Optional<GodinaStudija> optional = godinaStudijaRepository.findById(id);
        if (optional.isPresent()) {
            GodinaStudija godinaStudija = optional.get();
            godinaStudija.setObrisano(false);
            godinaStudijaRepository.save(godinaStudija);
        }
    }
}