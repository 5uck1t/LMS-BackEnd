package com.example.demo.service;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.StudentNaGodini;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UverenjePDFGeneratorService {

    @Autowired
    private StudentNaGodiniService studentNaGodiniService;

    public byte[] generateUverenjePdf(Long id) throws Exception {

        StudentNaGodiniDTO student = studentNaGodiniService.findById(id).orElseThrow(()-> new ResourceNotFoundException("student na godini with id:" + id + " not found"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Rectangle customSize = new Rectangle(600, 350);
        Document document = new Document(customSize);
        PdfWriter.getInstance(document, out);
        document.open();

        String punoIme = student.getStudent().getOsoba().getIme() + " " + student.getStudent().getOsoba().getPrezime();
        String jmbg = student.getStudent().getOsoba().getJmbg();
        String smer = student.getGodinaStudija().getStudijskiProgram().getNaziv();
        String godina = student.getGodinaStudija().getGodina();
        String adresa = student.getStudent().getOsoba().getAdresa().getUlica() + " " + student.getStudent().getOsoba().getAdresa().getBroj() + "," + student.getStudent().getOsoba().getAdresa().getMesto().getNaziv();
        String drzava = student.getStudent().getOsoba().getAdresa().getMesto().getDrzava().getNaziv();
        String indeks = student.getBrojIndeksa().toString();
        String dekan = student.getGodinaStudija().getStudijskiProgram().getKatedra().getFakultet().getDekan().getOsoba().getIme() + " " + student.getGodinaStudija().getStudijskiProgram().getKatedra().getFakultet().getDekan().getOsoba().getPrezime();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String formattedDate = today.format(formatter);

        InputStream fontStream = new ClassPathResource("fonts/DejaVuSerif.ttf").getInputStream();
        byte[] fontBytes = fontStream.readAllBytes();
        BaseFont baseFont = BaseFont.createFont("DejaVuSerif.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontBytes, null);
        Font font = new Font(baseFont, 12);

        Paragraph title = new Paragraph("UVERENJE", font);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(15f);
        title.setSpacingBefore(15f);

        document.add(new Paragraph("На основу члана 102. става 2. Закона о средњој школи...", font));
        document.add(title);
        document.add(new Paragraph("Daje se " + punoIme + " (ime i prezime studenta)", font));
        document.add(new Paragraph("JMBG " + jmbg + " adresa " + adresa + " sa indeksom " + indeks, font));
        document.add(new Paragraph("da je zavrsio/la " + godina + "." + "godine III godinu sa dovoljnim (2,20) uspehom.", font));
        document.add(new Paragraph("Smer: " + smer, font));
        document.add(new Paragraph("Izdato " + formattedDate + " године.", font));

        Paragraph dekanFakulteta = new Paragraph("Dekan Fakulteta.", font);
        dekanFakulteta.setAlignment(Element.ALIGN_RIGHT);
        dekanFakulteta.setSpacingBefore(15f);
        document.add(dekanFakulteta);

        Paragraph dekanIme = new Paragraph(dekan, font);
        dekanIme.setAlignment(Element.ALIGN_RIGHT);
        document.add(dekanIme);

        document.close();
        return out.toByteArray();
    }
}
