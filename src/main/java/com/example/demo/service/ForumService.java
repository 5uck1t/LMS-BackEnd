package com.example.demo.service;

import com.example.demo.dto.ForumDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Forum;
import com.example.demo.model.ForumHasKorisnik;
import com.example.demo.repository.ForumRepository;
import com.example.demo.saveDto.ForumSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    public List<ForumDTO> findAll() {
        return ((List<Forum>) forumRepository.findAll())
                .stream()
                .map(Forum::toDto)
                .collect(Collectors.toList());
    }

    public List<ForumDTO> findAllActive() {
        return ((List<Forum>) forumRepository.findByObrisanoFalse())
                .stream()
                .map(Forum::toDto)
                .collect(Collectors.toList());
    }

    public List<ForumDTO> findAllDeleted() {
        return ((List<Forum>) forumRepository.findByObrisanoTrue())
                .stream()
                .map(Forum::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ForumDTO> findById(Long id) {
        return forumRepository.findById(id).map(Forum::toDto);
    }

    public Optional<ForumDTO> findByNaziv(String naziv) {
        return forumRepository.findByNaziv(naziv).map(Forum::toDto);
    }

    public Optional<Forum> findEntityById(Long id) {
        return forumRepository.findById(id);
    }

    public ForumDTO save(ForumSaveDTO forum) {

        Forum novi = forum.toEntity();

        if(forum.getForum_id() != null) {
            novi.setForum(forumRepository.findById(forum.getForum_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Forum with id:" + forum.getForum_id() + " not found")));
        }
        return forumRepository.save(novi).toDto();
    }

    public void delete(ForumDTO forum) {
        forum.setObrisano(true);
        forumRepository.save(forum.toEntity());
    }

    public void delete(Long id) {
        Optional<Forum> optional = forumRepository.findById(id);
        if (optional.isPresent()) {
            Forum forum = optional.get();
            forum.setObrisano(true);
            forumRepository.save(forum);
        }
    }

    public void vrati(ForumDTO forum) {
        forum.setObrisano(false);
        forumRepository.save(forum.toEntity());
    }

    public void vrati(Long id) {
        Optional<Forum> optional = forumRepository.findById(id);
        if (optional.isPresent()) {
            Forum forum = optional.get();
            forum.setObrisano(false);
            forumRepository.save(forum);
        }
    }
}