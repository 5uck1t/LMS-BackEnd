package com.example.demo.controller;

import com.example.demo.dto.IzvestajPolaganjaDTO;
import com.example.demo.service.IzvestajPolaganjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/izvestaji/polaganja")
public class IzvestajPolaganjaController {

    @Autowired
    private IzvestajPolaganjaService service;

    // Primer: /api/izvestaji/polaganja?rokId=1&predmetId=10
    @GetMapping
    public ResponseEntity<IzvestajPolaganjaDTO> getIzvestaj(
            @RequestParam Long rokId,
            @RequestParam Long predmetId
    ) {
        IzvestajPolaganjaDTO dto = service.getIzvestaj(rokId, predmetId);
        return ResponseEntity.ok(dto);
    }
}
