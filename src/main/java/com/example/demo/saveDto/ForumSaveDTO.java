package com.example.demo.saveDto;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.ForumHasKorisnikDTO;
import com.example.demo.dto.ObavestenjeDTO;
import com.example.demo.model.Forum;

import java.util.Set;

public class ForumSaveDTO {

    private Long id;
    private String naziv;
    private Long forum_id;
    private Boolean obrisano = false;

    public ForumSaveDTO(Long id, String naziv, Long forum_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.forum_id = forum_id;
        this.obrisano = obrisano;
    }

    public ForumSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getForum_id() {
        return forum_id;
    }

    public void setForum_id(Long forum_id) {
        this.forum_id = forum_id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }


    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Forum toEntity() {
        Forum e = new Forum();
        e.setId(id);
        e.setNaziv(naziv);
        e.setForumi(null);
        e.setObavjestenja(null);
        e.setForumHasKorinsik(null);
        e.setObrisano(obrisano);
        return e;
    }
}
