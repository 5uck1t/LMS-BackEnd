package com.example.demo.service;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.ForumHasKorisnikDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Fakultet;
import com.example.demo.model.Forum;
import com.example.demo.model.ForumHasKorisnik;
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
}