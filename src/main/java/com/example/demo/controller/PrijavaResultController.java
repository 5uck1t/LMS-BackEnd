package com.example.demo.controller;

import com.example.demo.dto.PrijavaPolaganjaUploadDTO;
import com.example.demo.service.PrijavaPolaganjaImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prijave")
public class PrijavaResultController {

    @Autowired
    private PrijavaPolaganjaImportService importService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadPrijave(@RequestBody PrijavaPolaganjaUploadDTO dto) {
        try {
            Map<String, Object> res = importService.importPrijave(dto);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPrijavePdf() {
        try {
            byte[] pdf = importService.exportPrijaveToPdf();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"prijave_polaganja.pdf\"")
                    .body(pdf);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
