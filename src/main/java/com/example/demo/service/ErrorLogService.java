package com.example.demo.service;

import com.example.demo.dto.FakultetDTO;
import com.example.demo.model.Drzava;
import com.example.demo.model.ErrorLog;
import com.example.demo.model.Fakultet;
import com.example.demo.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ErrorLogService {

    @Autowired
    private ErrorRepository errorRepository;

    public Iterable<ErrorLog> findAll() {
        return errorRepository.findAll();
    }

    public void delete(Long id) {
        Optional<ErrorLog> optional = errorRepository.findById(id);
        if (optional.isPresent()) {
            ErrorLog error = optional.get();
            error.setObrisano(true);
            errorRepository.save(error);
        }
    }

    public void vrati(Long id) {
        Optional<ErrorLog> optional = errorRepository.findById(id);
        if (optional.isPresent()) {
            ErrorLog errorLog = optional.get();
            errorLog.setObrisano(false);
            errorRepository.save(errorLog);
        }
    }

    public List<ErrorLog> findAllActive() {
        return errorRepository.findByObrisanoFalse();
    }

    public List<ErrorLog> findAllDeleted() {

        return errorRepository.findByObrisanoTrue();
    }
}
