package com.example.demo.service;

import com.example.demo.dto.IzvestajPolaganjaDTO;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class IzvestajPolaganjaPdfService {

    @Autowired
    private PrijavaPolaganjaRepository prijavaRepo;

    public byte[] generatePdf(IzvestajPolaganjaDTO dto) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        // Naslov
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph("Izveštaj o polaganju ispita", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph(" "));

        // Meta informacije
        Font metaFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        document.add(new Paragraph("Rok: " + dto.getRokNaziv() + " (ID: " + dto.getRokId() + ")", metaFont));
        document.add(new Paragraph("Predmet: " + dto.getPredmetNaziv() + " (ID: " + dto.getPredmetId() + ")", metaFont));
        document.add(new Paragraph("Broj prijava: " + dto.getBrojPrijava() +
                ", Prosek bodova: " + (dto.getProsekBodova() != null ? dto.getProsekBodova() : "-") +
                ", Min: " + (dto.getMinBodovi() != null ? dto.getMinBodovi() : "-") +
                ", Max: " + (dto.getMaxBodovi() != null ? dto.getMaxBodovi() : "-"), metaFont));
        document.add(new Paragraph(" "));

        // Histogram
        PdfPTable histTable = new PdfPTable(2);
        histTable.setWidths(new int[]{2, 2});
        histTable.addCell("Ocena");
        histTable.addCell("Broj studenata");

        dto.getHistogram().forEach((ocena, broj) -> {
            histTable.addCell(String.valueOf(ocena));
            histTable.addCell(String.valueOf(broj));
        });
        document.add(histTable);
        document.add(new Paragraph(" "));

        // Tabela rezultata
        PdfPTable table = new PdfPTable(6);
        table.setWidths(new int[]{2, 3, 3, 3, 2, 2});
        table.addCell("Polaganje ID");
        table.addCell("Datum");
        table.addCell("Student");
        table.addCell("Indeks");
        table.addCell("Bodovi");
        table.addCell("Ocena");

        for (var r : dto.getRezultati()) {
            table.addCell(String.valueOf(r.getPolaganjeId()));
            table.addCell(r.getDatum() != null ? r.getDatum().toString() : "-");
            table.addCell(r.getStudent() != null ? r.getStudent() : "-");
            table.addCell(r.getIndeks() != null ? r.getIndeks() : "-");
            table.addCell(r.getBodovi() != null ? String.valueOf(r.getBodovi()) : "-");
            table.addCell(r.getOcena() != null ? String.valueOf(r.getOcena()) : "-");
        }
        document.add(table);

        document.close();
        return out.toByteArray();
    }
}
