package com.example.demo.controller;
import com.example.demo.service.UverenjePDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    private UverenjePDFGeneratorService generator;

    @GetMapping("/uverenje/{id}")
    public ResponseEntity<byte[]> downloadUverenjePdf(@PathVariable Long id) {
        try {
            byte[] pdfBytes = generator.generateUverenjePdf(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=uverenje.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
