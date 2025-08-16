package com.example.demo.service;

import com.example.demo.dto.PrijavaPolaganjaUploadDTO;
import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.model.Polaganje;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.example.demo.repository.PohadjanjePredmetaRepository;
import com.example.demo.repository.PolaganjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import java.io.*;
import java.time.ZoneId;
import java.util.*;
import org.w3c.dom.*;

@Service
@Transactional
public class PrijavaPolaganjaImportService {

    @Autowired
    private PrijavaPolaganjaRepository prijavaRepo;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    @Autowired
    private PolaganjeRepository polaganjeRepo;

    private void validateXml(String xml) throws Exception {
        ClassPathResource res = new ClassPathResource("schemas/prijava-polaganja.xsd");
        if (!res.exists()) return;
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(res.getFile());
        Validator validator = schema.newValidator();
        try (StringReader sr = new StringReader(xml)) {
            validator.validate(new StreamSource(sr));
        }
    }

    private List<Map<String, String>> parsePrijaveFromXml(String xml) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        try (InputStream is = new ByteArrayInputStream(xml.getBytes())) {
            Document doc = db.parse(is);
            NodeList list = doc.getElementsByTagName("prijava");
            List<Map<String, String>> out = new ArrayList<>();
            for (int i = 0; i < list.getLength(); i++) {
                Element el = (Element) list.item(i);
                Map<String, String> map = new HashMap<>();
                if (el.getElementsByTagName("pohadjanjeId").getLength() > 0)
                    map.put("pohadjanjeId", el.getElementsByTagName("pohadjanjeId").item(0).getTextContent());
                map.put("polaganjeId", el.getElementsByTagName("polaganjeId").item(0).getTextContent());
                if (el.getElementsByTagName("brojBodova").getLength() > 0)
                    map.put("brojBodova", el.getElementsByTagName("brojBodova").item(0).getTextContent());
                if (el.getElementsByTagName("datum").getLength() > 0)
                    map.put("datum", el.getElementsByTagName("datum").item(0).getTextContent());
                out.add(map);
            }
            return out;
        }
    }

    /**
     * Importuje prijave polaganja iz XML sadržaja ili fajla.
     * Vraća mapu sa brojem uspešnih i listom grešaka.
     */
    public Map<String, Object> importPrijave(PrijavaPolaganjaUploadDTO dto) throws Exception {
        String xml;

        if (dto.getXmlContent() != null && !dto.getXmlContent().isBlank()) {
            xml = dto.getXmlContent();
        } else {
            throw new IllegalArgumentException("Morate uneti xmlContent");
        }

        validateXml(xml);

        List<Map<String, String>> parsed = parsePrijaveFromXml(xml);

        List<String> errors = new ArrayList<>();
        int successCount = 0;

        for (Map<String, String> r : parsed) {
            try {
                Long polaganjeId = Long.parseLong(r.get("polaganjeId"));
                Polaganje polaganje = polaganjeRepo.findById(polaganjeId)
                        .orElseThrow(() -> new IllegalArgumentException("Polaganje ne postoji: " + polaganjeId));

                PohadjanjePredmeta poh = null;
                if (r.containsKey("pohadjanjeId")) {
                    Long pohId = Long.parseLong(r.get("pohadjanjeId"));
                    poh = pohadjanjeRepo.findById(pohId)
                            .orElseThrow(() -> new IllegalArgumentException("Pohadjanje predmeta ne postoji: " + pohId));
                } else {
                    errors.add("Nedostaje pohadjanjeId u prijavi: " + r);
                    continue;
                }

                Double brojBodova = r.containsKey("brojBodova") ? Double.parseDouble(r.get("brojBodova")) : null;

                Date datum = new Date();
                if (r.containsKey("datum")) {
                    datum = Date.from(java.time.LocalDateTime.parse(r.get("datum")).atZone(ZoneId.systemDefault()).toInstant());
                }

                // Uklonjen deo sa existingOpt jer uvek praviš novu prijavu
                PrijavaPolaganja prijava = new PrijavaPolaganja();
                prijava.setPohadjanjePredmeta(poh);
                prijava.setPolaganje(polaganje);
                prijava.setBrojBodova(brojBodova);
                prijava.setDatum(datum);
                prijava.setObrisano(false);


                prijavaRepo.save(prijava);
                successCount++;
            } catch (Exception e) {
                errors.add("Greška u obradi prijave " + r + ": " + e.getMessage());
            }
        }

        Map<String, Object> res = new HashMap<>();
        res.put("successCount", successCount);
        res.put("errors", errors);
        return res;
    }


    /**
     * Generiše PDF sa svim prijavama polaganja.
     */
    public byte[] exportPrijaveToPdf() throws Exception {
    	Iterable<PrijavaPolaganja> iterablePrijave = prijavaRepo.findAll();
    	List<PrijavaPolaganja> prijave = new ArrayList<>();
    	iterablePrijave.forEach(prijave::add);

        com.lowagie.text.Document document = new com.lowagie.text.Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        com.lowagie.text.pdf.PdfWriter.getInstance(document, out);
        document.open();

        com.lowagie.text.Font font = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA_BOLD, 14);
        document.add(new com.lowagie.text.Paragraph("Izveštaj o prijavama polaganja", font));
        document.add(new com.lowagie.text.Paragraph(" "));

        com.lowagie.text.pdf.PdfPTable table = new com.lowagie.text.pdf.PdfPTable(5);
        table.setWidths(new int[]{2, 3, 3, 3, 2});
        table.addCell("ID prijave");
        table.addCell("Polaganje ID");
        table.addCell("Student");
        table.addCell("Broj bodova");
        table.addCell("Datum");

        for (PrijavaPolaganja p : prijave) {
            table.addCell(String.valueOf(p.getId()));
            table.addCell(p.getPolaganje() != null ? String.valueOf(p.getPolaganje().getId()) : "-");

            String studentName = "-";
            if (p.getPohadjanjePredmeta() != null && p.getPohadjanjePredmeta().getStudentNaGodini() != null
                    && p.getPohadjanjePredmeta().getStudentNaGodini().getStudent() != null) {
                var osoba = p.getPohadjanjePredmeta().getStudentNaGodini().getStudent().getOsoba();
                if (osoba != null)
                    studentName = osoba.getIme() + " " + osoba.getPrezime();
            }
            table.addCell(studentName);
            table.addCell(p.getBrojBodova() != null ? p.getBrojBodova().toString() : "-");
            table.addCell(p.getDatum() != null ? p.getDatum().toString() : "-");
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }
}
