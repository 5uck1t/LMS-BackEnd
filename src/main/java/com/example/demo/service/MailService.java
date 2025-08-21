package com.example.demo.service;

import com.example.demo.repository.NastavnikRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NastavnikRepository nastavnikRepo;

    /**
     * Šalje izveštaj nastavnicima angažovanim na predmetu.
     */
    public void sendIzvestajToNastavnici(Long predmetId, byte[] pdfBytes) throws Exception {
        List<String> emails = nastavnikRepo.findEmailsByPredmetId(predmetId);

        if (emails == null || emails.isEmpty()) {
            throw new RuntimeException("Nema nastavnika angažovanih na predmetu " + predmetId);
        }

        for (String email : emails) {
            sendEmailWithAttachment(
                email,
                "Izveštaj o polaganju ispita",
                "U prilogu se nalazi izveštaj o rezultatima polaganja.",
                pdfBytes,
                "izvestaj.pdf"
            );
        }
    }

    /**
     * Helper metoda za slanje mejla sa PDF prilogom.
     */
    private void sendEmailWithAttachment(String to, String subject, String text,
                                         byte[] attachment, String filename) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
        helper.addAttachment(filename, new ByteArrayResource(attachment));

        mailSender.send(message);
    }
}
