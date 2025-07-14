package com.example.demo.service;

import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.EvaluacijaPrijavaDTO;
import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.DatumPredmetaRepository;
import com.example.demo.repository.EvaluacijaZnanjaRepository;
import com.example.demo.repository.PohadjanjePredmetaRepository;
import com.example.demo.repository.RokRepository;
import com.example.demo.saveDto.EvaluacijaZnanjaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EvaluacijaZnanjaService {

    @Autowired
    private EvaluacijaZnanjaRepository evaluacijaZnanjaRepository;

    @Autowired
    private DatumPredmetaService datumPredmetaService;

    @Autowired
    private RokService rokService;
    
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepository;

    @Autowired
    private RokRepository rokRepository;


    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;

    public List<EvaluacijaZnanjaDTO> findAll() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findAll())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluacijaZnanjaDTO> findAllActive() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findByObrisanoFalse())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluacijaZnanjaDTO> findAllDeleted() {

        return ((List<EvaluacijaZnanja>) evaluacijaZnanjaRepository.findByObrisanoTrue())
                .stream()
                .map(EvaluacijaZnanja::toDto)
                .collect(Collectors.toList());
    }

    public Optional<EvaluacijaZnanjaDTO> findById(Long id) {
        return evaluacijaZnanjaRepository.findById(id).map(EvaluacijaZnanja::toDto);
    }

    public Optional<EvaluacijaZnanja> findEntityById(Long id) {
        return evaluacijaZnanjaRepository.findById(id);
    }

    public EvaluacijaZnanjaDTO save(EvaluacijaZnanjaSaveDTO evaluacijaZnanja) {

        EvaluacijaZnanja nova = evaluacijaZnanja.toEntity();

        nova.setRok(rokService.findEntityById(evaluacijaZnanja.getRok_id())
                .orElseThrow(() -> new EntityNotFoundException("Rok with id:" + evaluacijaZnanja.getRok_id() + " not found")));

        nova.setPohadjanjepredmeta(pohadjanjePredmetaService.findEntityById(evaluacijaZnanja.getPohadjanjepredmeta_id())
                .orElseThrow(() -> new EntityNotFoundException("Pohadjanje predmeta with id:" + evaluacijaZnanja.getPohadjanjepredmeta_id() + " not found")));

        //find datum for evaluacija znanja by rok id from evaluacija znanja and predmet id also from evaluacijaZnanja>pohadjanjePredmeta>predmet
        Optional<DatumPredmetaDTO> datum = datumPredmetaService.findByRokIdAndPredmetId(evaluacijaZnanja.getRok_id(), nova.getPohadjanjepredmeta().getRealizacijaPredmeta().getPredmet().getId());

        //if datum doesn't exist don't change anything
        if(datum.isEmpty()){
            throw new ResourceNotFoundException("DatumPredmeta not found");
        }

        //if datum exists set datum for evaluacijaZnanja
        nova.setDatum(datum.get().getDatum());
        return evaluacijaZnanjaRepository.save(nova).toDto();
    }

    public void delete(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        evaluacijaZnanja.setObrisano(true);
        evaluacijaZnanjaRepository.save(evaluacijaZnanja.toEntity());
    }

    public void delete(Long id) {
        Optional<EvaluacijaZnanja> optional = evaluacijaZnanjaRepository.findById(id);
        if (optional.isPresent()) {
            EvaluacijaZnanja evaluacijaZnanja = optional.get();
            evaluacijaZnanja.setObrisano(true);
            evaluacijaZnanjaRepository.save(evaluacijaZnanja);
        }
    }

    public void vrati(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        evaluacijaZnanja.setObrisano(false);
        evaluacijaZnanjaRepository.save(evaluacijaZnanja.toEntity());
    }

    public void vrati(Long id) {
        Optional<EvaluacijaZnanja> optional = evaluacijaZnanjaRepository.findById(id);
        if (optional.isPresent()) {
            EvaluacijaZnanja evaluacijaZnanja = optional.get();
            evaluacijaZnanja.setObrisano(false);
            evaluacijaZnanjaRepository.save(evaluacijaZnanja);
        }
    }
    
    public EvaluacijaZnanjaDTO prijaviIspit(EvaluacijaPrijavaDTO dto) {
        Optional<PohadjanjePredmeta> pohadjanje = pohadjanjePredmetaRepository.findById(dto.getPohadjanjePredmetaId());
        Optional<Rok> rok = rokRepository.findById(dto.getRokId());

        if (pohadjanje.isEmpty() || rok.isEmpty()) {
            throw new IllegalArgumentException("Nepostojeći pohadjanjePredmeta ili rok.");
        }

        Date danas = new Date();
        if (danas.before(rok.get().getPocetak()) || danas.after(rok.get().getKraj())) {
            throw new IllegalStateException("Rok trenutno nije aktivan.");
        }

        // Proveri da li već postoji prijava za isti predmet i rok
        boolean postoji = evaluacijaZnanjaRepository.existsByPohadjanjepredmetaAndRok(pohadjanje.get(), rok.get());
        if (postoji) {
            throw new IllegalStateException("Već ste prijavili ispit za ovaj predmet u datom roku.");
        }

        EvaluacijaZnanja nova = new EvaluacijaZnanja();
        nova.setNaziv(dto.getNaziv());
        nova.setDatum(dto.getDatum());
        nova.setBrojBodova(null); // Prijava bez ocene
        nova.setPohadjanjepredmeta(pohadjanje.get());
        nova.setRok(rok.get());
        nova.setObrisano(false);

        return evaluacijaZnanjaRepository.save(nova).toDto();
    }
}