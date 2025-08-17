package com.example.demo.service;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.dto.ZadatakDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EvaluacijaZnanja;
import com.example.demo.model.Odgovor;
import com.example.demo.model.Zadatak;
import com.example.demo.repository.EvaluacijaZnanjaRepository;
import com.example.demo.repository.OdgovorRepository;
import com.example.demo.repository.ZadatakRepository;
import com.example.demo.saveDto.PredmetSaveDTO;
import com.example.demo.saveDto.ZadatakSaveDTO;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZadatakService {

    @Autowired
    private ZadatakRepository zadatakRepository;

    @Autowired
    private EvaluacijaZnanjaService evaluacijaZnanjaService;
    
    @Autowired
    private EvaluacijaZnanjaRepository evaluacijaZnanjaRepository;
    
    @Autowired
    private OdgovorRepository odgovorRepository;
    

    public List<ZadatakDTO> findAll() {
        return ((List<Zadatak>) zadatakRepository.findAll())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findAllActive() {
        return ((List<Zadatak>) zadatakRepository.findByObrisanoFalse())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findAllDeleted() {
        return ((List<Zadatak>) zadatakRepository.findByObrisanoTrue())
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public List<ZadatakDTO> findByEvaluacijaId(Long id) {
        return ((List<Zadatak>) zadatakRepository.findByEvaluacijaZnanja_Id(id))
                .stream()
                .map(Zadatak::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ZadatakDTO> findById(Long id) {
        return zadatakRepository.findById(id).map(Zadatak::toDto);
    }

    public Optional<Zadatak> findEntityById(Long id) {
        return zadatakRepository.findById(id);
    }

    public ZadatakDTO save(ZadatakSaveDTO dto) {
        System.out.println("=== Primljen ZadatakSaveDTO ===");
        System.out.println("Pitanje: " + dto.getPitanje());
        System.out.println("Evaluacija ID: " + dto.getEvaluacijaZnanja_id());

        if (dto.getOdgovori() == null || dto.getOdgovori().isEmpty()) {
            System.out.println("Broj odgovora: 0");
        } else {
            System.out.println("Broj odgovora: " + dto.getOdgovori().size());
            for (int i = 0; i < dto.getOdgovori().size(); i++) {
                String odgovorTekst = dto.getOdgovori().get(i).getOdgovor();
                System.out.println("Odgovor[" + i + "]: " + odgovorTekst);
            }
        }

        // Pronađi evaluaciju
        EvaluacijaZnanja evaluacija = evaluacijaZnanjaRepository
                .findById(dto.getEvaluacijaZnanja_id())
                .orElseThrow(() -> new EntityNotFoundException("Evaluacija nije pronađena"));

        // Kreiraj entitet Zadatak iz DTO-a
        Zadatak zadatak = dto.toEntity();
        zadatak.setEvaluacijaZnanja(evaluacija);

        // Sačuvaj zadatak
        zadatak = zadatakRepository.save(zadatak);
        System.out.println("Zadatak sačuvan sa ID: " + zadatak.getId());

        // Kreiraj listu odgovora iz DTO-a
        List<Odgovor> odgovori = dto.toOdgovorEntities(zadatak);
        System.out.println("Generisano odgovora: " + odgovori.size());
        for (Odgovor o : odgovori) {
            System.out.println("Odgovor: " + o.getOdgovor() + ", vezan za zadatak ID: " + zadatak.getId());
        }

        // Sačuvaj odgovore
        odgovorRepository.saveAll(odgovori);
        System.out.println("Svi odgovori sačuvani.");

        return zadatak.toDto();
    }

    public void delete(ZadatakDTO zadatak) {
        zadatak.setObrisano(true);
        zadatakRepository.save(zadatak.toEntity());
    }

    public void delete(Long id) {
        Optional<Zadatak> optional = zadatakRepository.findById(id);
        if (optional.isPresent()) {
            Zadatak zadatak = optional.get();
            zadatak.setObrisano(true);
            zadatakRepository.save(zadatak);
        }
    }

    public void vrati(ZadatakDTO zadatak) {
        zadatak.setObrisano(false);
        zadatakRepository.save(zadatak.toEntity());
    }

    public void vrati(Long id) {
        Optional<Zadatak> optional = zadatakRepository.findById(id);
        if (optional.isPresent()) {
            Zadatak zadatak = optional.get();
            zadatak.setObrisano(false);
            zadatakRepository.save(zadatak);
        }
    }
}
