package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.ForumHasKorisnik;
import com.example.demo.saveDto.ForumHasKorisnikSaveDTO;

public class ForumHasKorisnikDTO {

    private Long id;
    private UlogovaniKorisnikDTO ulogovaniKorisnik;
    private ForumDTO forum;
    private Boolean obrisano = false;

    public ForumHasKorisnikDTO() {
    }

    public ForumHasKorisnikDTO(Long id, UlogovaniKorisnikDTO ulogovaniKorisnik, ForumDTO forum, Boolean obrisano) {
        this.id = id;
        this.ulogovaniKorisnik = ulogovaniKorisnik;
        this.forum = forum;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UlogovaniKorisnikDTO getUlogovaniKorisnik() { return ulogovaniKorisnik; }
    public void setUlogovaniKorisnik(UlogovaniKorisnikDTO ulogovaniKorisnik) { this.ulogovaniKorisnik = ulogovaniKorisnik; }

    public ForumDTO getForum() { return forum; }
    public void setForum(ForumDTO forum) { this.forum = forum; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public ForumHasKorisnik toEntity() {
        ForumHasKorisnik e = new ForumHasKorisnik();
        e.setId(id);
        e.setUlogovaniKorisnik(ulogovaniKorisnik.toEntity());
        e.setForum(forum.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public ForumHasKorisnikSaveDTO toSaveDto() {
        ForumHasKorisnikSaveDTO e = new ForumHasKorisnikSaveDTO();
        e.setId(id);
        e.setUlogovaniKorisnik_id(ulogovaniKorisnik.getId());
        e.setForum_id(forum.getId());
        e.setObrisano(obrisano);
        return e;
    }

}