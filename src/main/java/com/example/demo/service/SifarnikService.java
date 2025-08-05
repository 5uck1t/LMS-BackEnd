package com.example.demo.service;

import com.example.demo.model.Sifarnik;
import com.example.demo.repository.SifarnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SifarnikService {

    @Autowired
    private SifarnikRepository sifarnikRepository;

    public Iterable<Sifarnik> findAll() {
        return  sifarnikRepository.findAll();
    }

    public List<Sifarnik> findAllActive() {
        return  sifarnikRepository.findByObrisanoFalse();
    }

    public List<Sifarnik> findAllDeleted() {
        return  sifarnikRepository.findByObrisanoTrue();
    }

    public Optional<Sifarnik> findById(Long id) {
        return sifarnikRepository.findById(id);
    }

    public Optional<Sifarnik> findEntityById(Long id) {
        return sifarnikRepository.findById(id);
    }

    public Sifarnik save(Sifarnik sifarnik) {
        return sifarnikRepository.save(sifarnik);
    }

    public void delete(Sifarnik sifarnik) {
        sifarnik.setObrisano(true);
        sifarnikRepository.save(sifarnik);
    }

    public void delete(Long id) {
        Optional<Sifarnik> optional = sifarnikRepository.findById(id);
        if (optional.isPresent()) {
            Sifarnik sifarnik = optional.get();
            sifarnik.setObrisano(true);
            sifarnikRepository.save(sifarnik);
        }
    }

    public void vrati(Sifarnik sifarnik) {
        sifarnik.setObrisano(false);
        sifarnikRepository.save(sifarnik);
    }

    public void vrati(Long id) {
        Optional<Sifarnik> optional = sifarnikRepository.findById(id);
        if (optional.isPresent()) {
            Sifarnik sifarnik = optional.get();
            sifarnik.setObrisano(false);
            sifarnikRepository.save(sifarnik);
        }
    }
}
