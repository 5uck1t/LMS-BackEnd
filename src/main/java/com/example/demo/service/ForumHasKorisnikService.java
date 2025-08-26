package com.example.demo.service;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.ForumHasKorisnikDTO;
import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.NastavnikForumDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Fakultet;
import com.example.demo.model.Forum;
import com.example.demo.model.ForumHasKorisnik;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.repository.ForumHasKorisnikRepository;
import com.example.demo.saveDto.ForumHasKorisnikSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumHasKorisnikService {

    @Autowired
    private ForumHasKorisnikRepository forumHasKorisnikRepository;

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;
    
    @Autowired
    private NastavnikService nastavnikService;

    @Autowired
    private ForumService forumService;

    public List<ForumHasKorisnikDTO> findAll() {
        return ((List<ForumHasKorisnik>) forumHasKorisnikRepository.findAll())
                .stream()
                .map(ForumHasKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public List<ForumHasKorisnikDTO> findAllActive() {
        return ((List<ForumHasKorisnik>) forumHasKorisnikRepository.findByObrisanoFalse())
                .stream()
                .map(ForumHasKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public List<ForumHasKorisnikDTO> findAllDeleted() {
        return ((List<ForumHasKorisnik>) forumHasKorisnikRepository.findByObrisanoTrue())
                .stream()
                .map(ForumHasKorisnik::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ForumHasKorisnikDTO> findById(Long id) {
        return forumHasKorisnikRepository.findById(id).map(ForumHasKorisnik::toDto);
    }

    public Optional<ForumHasKorisnik> findEntityById(Long id) {
        return forumHasKorisnikRepository.findById(id);
    }

    public ForumHasKorisnikDTO save(ForumHasKorisnikSaveDTO forumHasKorisnik) {

        ForumHasKorisnik novi = forumHasKorisnik.toEntity();

        novi.setForum(forumService.findEntityById(forumHasKorisnik.getForum_id())
                .orElseThrow(() -> new ResourceNotFoundException("Forum with id:" + forumHasKorisnik.getForum_id() + " not found")));

        novi.setUlogovaniKorisnik(ulogovaniKorisnikService.findEntityById(forumHasKorisnik.getUlogovaniKorisnik_id())
                .orElseThrow(() -> new ResourceNotFoundException("Korisnik with id:" + forumHasKorisnik.getUlogovaniKorisnik_id() + " not found")));
        return forumHasKorisnikRepository.save(novi).toDto();
    }

    public void delete(ForumHasKorisnikDTO forumHasKorisnik) {
        forumHasKorisnik.setObrisano(true);
        forumHasKorisnikRepository.save(forumHasKorisnik.toEntity());
    }

    public void delete(Long id) {
        Optional<ForumHasKorisnik> optional = forumHasKorisnikRepository.findById(id);
        if (optional.isPresent()) {
            ForumHasKorisnik forumHasKorisnik = optional.get();
            forumHasKorisnik.setObrisano(true);
            forumHasKorisnikRepository.save(forumHasKorisnik);
        }
    }

    public void vrati(ForumHasKorisnikDTO forumHasKorisnik) {
        forumHasKorisnik.setObrisano(false);
        forumHasKorisnikRepository.save(forumHasKorisnik.toEntity());
    }

    public void vrati(Long id) {
        Optional<ForumHasKorisnik> optional = forumHasKorisnikRepository.findById(id);
        if (optional.isPresent()) {
            ForumHasKorisnik forumHasKorisnik = optional.get();
            forumHasKorisnik.setObrisano(false);
            forumHasKorisnikRepository.save(forumHasKorisnik);
        }
    }

    public List<ForumDTO> getForumsByUserId(Long userId) {
        return ((List<Forum>) forumHasKorisnikRepository.findActiveForumsByUserId(userId))
                .stream()
                .map(Forum::toDto)
                .collect(Collectors.toList());
    }
    
    public List<KorisnikDTO> findKorisniciByForumId(Long forumId) {
        List<UlogovaniKorisnik> korisnici = forumHasKorisnikRepository.findKorisniciByForumId(forumId);
        return korisnici.stream()
                .map(this::mapToDTO)
                .toList();
    }

    private KorisnikDTO mapToDTO(UlogovaniKorisnik k) {
        KorisnikDTO dto = new KorisnikDTO();
        dto.setId(k.getId());
        dto.setIme(k.getOsoba().getIme());
        dto.setPrezime(k.getOsoba().getPrezime());

        // Izvuci rolu iz dodeljenih prava pristupa
        String rola = k.getDodeljenaPravaPristupa()
                       .stream()
                       .findFirst() // ako je više rola, uzmi prvu
                       .map(d -> d.getPravoPristupa().getNaziv())
                       .orElse("ROLE_STUDENT"); // fallback
        dto.setRola(rola);

        // Postavi broj indeksa samo za studente
        if ("ROLE_STUDENT".equals(rola)) {
            Long brojIndeksa = k.getOsoba().getStudent().getStudentNaGodini().stream()
                                 .findFirst()
                                 .map(sng -> sng.getBrojIndeksa())
                                 .orElse(null);
            dto.setBrojIndeksa(brojIndeksa != null ? brojIndeksa.toString() : null);
        }

        return dto;
    }

    
    public void dodajStudenta(Long forumId, Long studentId) {
        UlogovaniKorisnik student = ulogovaniKorisnikService.findEntityById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        boolean isStudent = student.getDodeljenaPravaPristupa().stream()
                .anyMatch(r -> r.getPravoPristupa().getNaziv().equals("ROLE_STUDENT"));
        if (!isStudent) {
            throw new IllegalArgumentException("Samo studenti mogu biti dodati");
        }

        Forum forum = forumService.findEntityById(forumId)
                .orElseThrow(() -> new ResourceNotFoundException("Forum not found"));

        // Proveri da li već postoji zapis (obrisano ili ne)
        Optional<ForumHasKorisnik> optionalFhk = forumHasKorisnikRepository
                .findByForumAndUlogovaniKorisnik(forum, student);

        if (optionalFhk.isPresent()) {
            ForumHasKorisnik existing = optionalFhk.get();
            if (existing.getObrisano()) {
                existing.setObrisano(false);
                forumHasKorisnikRepository.save(existing);
            }
            // ako već postoji i nije obrisano, ništa ne radimo
            return;
        }

        // Ako ne postoji, kreiraj novi
        ForumHasKorisnik fhk = new ForumHasKorisnik();
        fhk.setForum(forum);
        fhk.setUlogovaniKorisnik(student);
        fhk.setObrisano(false);

        forumHasKorisnikRepository.save(fhk);
    }



    public void ukloniStudenta(Long forumId, Long studentId) {
        List<ForumHasKorisnik> lista = forumHasKorisnikRepository.findByObrisanoFalse().stream()
                .filter(fhk -> fhk.getForum().getId().equals(forumId)
                        && fhk.getUlogovaniKorisnik().getId().equals(studentId))
                .toList();

        for (ForumHasKorisnik fhk : lista) {
            UlogovaniKorisnik student = fhk.getUlogovaniKorisnik();
            boolean isStudent = student.getDodeljenaPravaPristupa().stream()
                    .anyMatch(r -> r.getPravoPristupa().getNaziv().equals("ROLE_STUDENT"));
            if (isStudent) {
                fhk.setObrisano(true);
                forumHasKorisnikRepository.save(fhk);
            }
        }
    }
    
    public List<NastavnikForumDTO> getAvailableNastavnici(Long forumId, String filter) {
        return nastavnikService.getAvailableNastavnici(forumId, filter);
    }


    public void dodajNastavnikaNaForum(Long forumId, Long ulogovaniKorisnikId) {
        UlogovaniKorisnik nastavnikUK = ulogovaniKorisnikService.findEntityById(ulogovaniKorisnikId)
                .orElseThrow(() -> new ResourceNotFoundException("Ulogovani korisnik not found"));

        Forum forum = forumService.findEntityById(forumId)
                .orElseThrow(() -> new ResourceNotFoundException("Forum not found"));

        Optional<ForumHasKorisnik> optionalFhk = forumHasKorisnikRepository
                .findByForumAndUlogovaniKorisnik(forum, nastavnikUK);

        if (optionalFhk.isPresent()) {
            ForumHasKorisnik existing = optionalFhk.get();
            if (existing.getObrisano()) {
                existing.setObrisano(false);
                forumHasKorisnikRepository.save(existing);
            }
            return;
        }

        ForumHasKorisnik fhk = new ForumHasKorisnik();
        fhk.setForum(forum);
        fhk.setUlogovaniKorisnik(nastavnikUK);
        fhk.setObrisano(false);

        forumHasKorisnikRepository.save(fhk);
    }


    public void ukloniNastavnikaSaForuma(Long forumId, Long nastavnikId) {
        forumHasKorisnikRepository.findByForumIdAndUlogovaniKorisnikId(forumId, nastavnikId)
            .forEach(fhk -> {
                fhk.setObrisano(true);
                forumHasKorisnikRepository.save(fhk);
            });
    }

}