package com.example.demo.service;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Adresa;
import com.example.demo.model.DatumPredmeta;
import com.example.demo.repository.AdresaRepository;
import com.example.demo.repository.DatumPredmetaRepository;
import com.example.demo.saveDto.DatumPredmetaSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DatumPredmetaService {

    @Autowired
    private DatumPredmetaRepository datumPredmetaRepository;

    @Autowired
    private RealizacijaPredmetaService realizacijaPredmetaService;

    @Autowired
    private RokService rokService;

    public List<DatumPredmetaDTO> findAll() {
        return ((List<DatumPredmeta>) datumPredmetaRepository.findAll())
                .stream()
                .map(DatumPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<DatumPredmetaDTO> findAllActive() {

        return ((List<DatumPredmeta>) datumPredmetaRepository.findByObrisanoFalse())
                .stream()
                .map(DatumPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public List<DatumPredmetaDTO> findAllDeleted() {

        return ((List<DatumPredmeta>) datumPredmetaRepository.findByObrisanoTrue())
                .stream()
                .map(DatumPredmeta::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DatumPredmetaDTO> findById(Long id) {
        return datumPredmetaRepository.findById(id).map(DatumPredmeta::toDto);
    }

    public Optional<DatumPredmeta> findEntityById(Long id) {
        return datumPredmetaRepository.findById(id);
    }

    public Optional<DatumPredmetaDTO> findByRokIdAndPredmetId(Long rokId, Long predmetId) {
        return datumPredmetaRepository.findOneByRokIdAndPredmetId(rokId,predmetId).map(DatumPredmeta::toDto);
    }

    public DatumPredmetaDTO save(DatumPredmetaSaveDTO datumPredmeta) {

        DatumPredmeta novi = datumPredmeta.toEntity();

        novi.setRok(rokService.findEntityById(datumPredmeta.getRok_id())
                .orElseThrow(() -> new ResourceNotFoundException("Rok with id:" + datumPredmeta.getRok_id() +" not found")));

        novi.setRealizacijaPredmeta(realizacijaPredmetaService.findEntityById(datumPredmeta.getRealizacijaPredmeta_id())
                .orElseThrow(() -> new ResourceNotFoundException("Realizacija Predmeta with id:"+datumPredmeta.getRealizacijaPredmeta_id() + " not found")));

        return datumPredmetaRepository.save(novi).toDto();
    }

    public void delete(DatumPredmetaDTO datumPredmeta) {
        datumPredmeta.setObrisano(true);
        datumPredmetaRepository.save(datumPredmeta.toEntity());
    }

    public void delete(Long id) {
        Optional<DatumPredmeta> optional = datumPredmetaRepository.findById(id);
        if (optional.isPresent()) {
            DatumPredmeta datumPredmeta = optional.get();
            datumPredmeta.setObrisano(true);
            datumPredmetaRepository.save(datumPredmeta);
        }
    }

    public void vrati(DatumPredmetaDTO datumPredmeta) {
        datumPredmeta.setObrisano(false);
        datumPredmetaRepository.save(datumPredmeta.toEntity());
    }

    public void vrati(Long id) {
        Optional<DatumPredmeta> optional = datumPredmetaRepository.findById(id);
        if (optional.isPresent()) {
            DatumPredmeta datumPredmeta = optional.get();
            datumPredmeta.setObrisano(false);
            datumPredmetaRepository.save(datumPredmeta);
        }
    }
}
