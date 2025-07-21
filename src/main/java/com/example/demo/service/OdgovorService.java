package com.example.demo.service;

import com.example.demo.dto.OdgovorDTO;
import com.example.demo.dto.OsobaDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Odgovor;
import com.example.demo.model.Osoba;
import com.example.demo.repository.OdgovorRepository;
import com.example.demo.saveDto.OdgovorSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdgovorService {

    @Autowired
    private OdgovorRepository odgovorRepository;

    @Autowired
    private ZadatakService zadatakService;

    public List<OdgovorDTO> findAll() {
        return ((List<Odgovor>) odgovorRepository.findAll())
                .stream()
                .map(Odgovor::toDto)
                .collect(Collectors.toList());
    }

    public List<OdgovorDTO> findAllActive() {
        return ((List<Odgovor>) odgovorRepository.findByObrisanoFalse())
                .stream()
                .map(Odgovor::toDto)
                .collect(Collectors.toList());
    }

    public List<OdgovorDTO> findAllDeleted() {
        return ((List<Odgovor>) odgovorRepository.findByObrisanoTrue())
                .stream()
                .map(Odgovor::toDto)
                .collect(Collectors.toList());
    }

    public List<OdgovorDTO> findByZadatakId(Long id) {
        return ((List<Odgovor>) odgovorRepository.findByZadatak_Id(id))
                .stream()
                .map(Odgovor::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OdgovorDTO> findById(Long id) {
        return odgovorRepository.findById(id).map(Odgovor::toDto);
    }

    public Optional<Odgovor> findEntityById(Long id) {
        return odgovorRepository.findById(id);
    }

    public OdgovorDTO save(OdgovorSaveDTO odgovor) {

        Odgovor novi = odgovor.toEntity();

        novi.setZadatak(zadatakService.findEntityById(odgovor.getZadatak_id())
                .orElseThrow(() -> new ResourceNotFoundException("Zadatak with id:" + odgovor.getZadatak_id() + " not found")));

        return odgovorRepository.save(novi).toDto();
    }

    public void delete(OdgovorDTO odgovor) {
        odgovor.setObrisano(true);
        odgovorRepository.save(odgovor.toEntity());
    }

    public void delete(Long id) {
        Optional<Odgovor> optional = odgovorRepository.findById(id);
        if (optional.isPresent()) {
            Odgovor odgovor = optional.get();
            odgovor.setObrisano(true);
            odgovorRepository.save(odgovor);
        }
    }

    public void vrati(OdgovorDTO odgovor) {
        odgovor.setObrisano(false);
        odgovorRepository.save(odgovor.toEntity());
    }

    public void vrati(Long id) {
        Optional<Odgovor> optional = odgovorRepository.findById(id);
        if (optional.isPresent()) {
            Odgovor odgovor = optional.get();
            odgovor.setObrisano(false);
            odgovorRepository.save(odgovor);
        }
    }
}
