package com.example.demo.controller;

import com.example.demo.dto.DrzavaDTO;
import com.example.demo.model.ErrorLog;
import com.example.demo.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
public class ErrorLogController {

    @Autowired
    private ErrorLogService errorLogService;

    @GetMapping
    public Iterable<ErrorLog> getAll() {
        return errorLogService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        errorLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<Void> restore(@PathVariable Long id) {
        errorLogService.vrati(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public List<ErrorLog> getAllActive() {
        return errorLogService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ErrorLog> getAllDeleted() {
        return errorLogService.findAllDeleted();
    }
}
