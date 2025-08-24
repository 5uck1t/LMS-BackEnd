package com.example.demo.controller;

import com.example.demo.dto.IzvestajPolaganjaDTO;
import com.example.demo.service.IzvestajPolaganjaPdfService;
import com.example.demo.service.IzvestajPolaganjaService;
import com.example.demo.service.MailService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/izvestaj")
public class IzvestajPolaganjaPdfController {

    @Autowired
    private IzvestajPolaganjaService izvestajService;

    @Autowired
    private IzvestajPolaganjaPdfService pdfService;
    
    @Autowired
    private MailService mailService;

    @PostMapping("/send-pdf")
    public ResponseEntity<?> sendPdfToNastavnici(@RequestParam Long rokId, @RequestParam Long predmetId) {
        try {
            IzvestajPolaganjaDTO dto = izvestajService.getIzvestaj(rokId, predmetId);
            byte[] pdf = pdfService.generatePdf(dto);

            mailService.sendIzvestajToNastavnici(predmetId, pdf);

            return ResponseEntity.ok(Map.of("message", "Izveštaj poslat nastavnicima"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdf(
            @RequestParam Long rokId,
            @RequestParam Long predmetId
    ) {
        try {
            // prvo dohvatimo podatke za izveštaj
            IzvestajPolaganjaDTO dto = izvestajService.getIzvestaj(rokId, predmetId);

            // generišemo PDF
            byte[] pdf = pdfService.generatePdf(dto);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"izvestaj_polaganja.pdf\"")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
}
