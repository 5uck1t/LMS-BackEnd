package com.example.demo.dto;

import com.example.demo.model.Forum;
import com.example.demo.saveDto.ForumSaveDTO;

import java.util.*;


public class ForumDTO {

    private Long id;
    private String naziv;
    private ForumDTO forum;
    private Set<ForumDTO> forumi;
    private Set<ObavestenjeDTO> obavjestenja;
    private Set<ForumHasKorisnikDTO> forumHasKorinsik;
    private Boolean obrisano = false;

    public ForumDTO() {
    }

    public ForumDTO(Long id, String naziv, ForumDTO forum, Set<ForumDTO> forumi, Set<ObavestenjeDTO> obavjestenja, Set<ForumHasKorisnikDTO> forumHasKorinsik, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.forum = forum;
        this.forumi = forumi;
        this.obavjestenja = obavjestenja;
        this.forumHasKorinsik = forumHasKorinsik;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public ForumDTO getForum() { return forum; }
    public void setForum(ForumDTO forum) { this.forum = forum; }

    public Set<ForumDTO> getForumi() { return forumi; }
    public void setForumi(Set<ForumDTO> forumi) { this.forumi = forumi; }

    public Set<ObavestenjeDTO> getObavjestenja() { return obavjestenja; }
    public void setObavjestenja(Set<ObavestenjeDTO> obavjestenja) { this.obavjestenja = obavjestenja; }

    public Set<ForumHasKorisnikDTO> getForumHasKorinsik() { return forumHasKorinsik; }
    public void setForumHasKorinsik(Set<ForumHasKorisnikDTO> forumHasKorinsik) { this.forumHasKorinsik = forumHasKorinsik; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Forum toEntity() {
        Forum e = new Forum();
        e.setId(id);
        e.setNaziv(naziv);
        e.setForum(forum.toEntity());
        e.setForumi(null);
        e.setObavjestenja(null);
        e.setForumHasKorinsik(null);
        e.setObrisano(obrisano);
        return e;
    }

    public ForumSaveDTO toSaveDto() {
        ForumSaveDTO e = new ForumSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setForum_id(forum.getId());
        e.setObrisano(obrisano);
        return e;
    }

}