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
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        String punoIme = student.getStudent().getOsoba().getIme() + " " + student.getStudent().getOsoba().getPrezime();
        String jmbg = student.getStudent().getOsoba().getJmbg();
        String smer = student.getGodinaStudija().getStudijskiProgram().getNaziv();
        String godina = student.getGodinaStudija().getGodina();
        String adresa = student.getStudent().getOsoba().getAdresa().getUlica() + " " + student.getStudent().getOsoba().getAdresa().getBroj() + "," + student.getStudent().getOsoba().getAdresa().getMesto().getNaziv();
        String drzava = student.getStudent().getOsoba().getAdresa().getMesto().getDrzava().getNaziv();

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        String formattedDate = today.format(formatter);

        InputStream fontStream = new ClassPathResource("fonts/DejaVuSerif.ttf").getInputStream();
        byte[] fontBytes = fontStream.readAllBytes();
        BaseFont baseFont = BaseFont.createFont("DejaVuSerif.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontBytes, null);
        Font font = new Font(baseFont, 12);

        document.add(new Paragraph("На основу члана 102. става 2. Закона о средњој школи...", font));
        document.add(new Paragraph("УВЕРЕЊЕ", font));
        document.add(new Paragraph("Даје се " + punoIme, font));
        document.add(new Paragraph("рођен-а 19.09.1963. Sušici, општина Prištine, држава Srbija", font));
        document.add(new Paragraph("завршио-ла" + godina + "године III (treći) разред са dovoljnim (2,20) успехом", font));
        document.add(new Paragraph("и стекао-ла III (treći) степен стручне спреме", font));
        document.add(new Paragraph("struka: " + smer, font));
        document.add(new Paragraph(formattedDate + "године.", font));
        document.add(new Paragraph("ДИРЕКТОР ШКОЛЕ.", font));
        document.add(new Paragraph("Малинка Митровић, дипл.ецц.", font));

        document.close();
        return out.toByteArray();
    }
}
