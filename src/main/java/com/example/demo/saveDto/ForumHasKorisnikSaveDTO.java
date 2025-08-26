package com.example.demo.saveDto;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.model.ForumHasKorisnik;

public class ForumHasKorisnikSaveDTO {

    private Long id;
    private Long ulogovaniKorisnik_id;
    private Long forum_id;
    private Boolean obrisano = false;

    public ForumHasKorisnikSaveDTO(Long id, Long ulogovaniKorisnik_id, Long forum_id, Boolean obrisano) {
        this.id = id;
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
        this.forum_id = forum_id;
        this.obrisano = obrisano;
    }

    public ForumHasKorisnikSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUlogovaniKorisnik_id() {
        return ulogovaniKorisnik_id;
    }

    public void setUlogovaniKorisnik_id(Long ulogovaniKorisnik_id) {
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
    }

    public Long getForum_id() {
        return forum_id;
    }

    public void setForum_id(Long forum_id) {
        this.forum_id = forum_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public ForumHasKorisnik toEntity() {
        ForumHasKorisnik e = new ForumHasKorisnik();
        e.setObrisano(obrisano);
        return e;
    }
}
