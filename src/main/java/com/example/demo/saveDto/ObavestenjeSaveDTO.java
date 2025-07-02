package com.example.demo.saveDto;

import com.example.demo.dto.ForumDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.model.Obavestenje;

import java.util.Date;

public class ObavestenjeSaveDTO {

    private Long id;
    private String naslov;
    private String tekstObavjestenja;
    private Date vremePostavljanja;
    private Long ulogovaniKorisnik_id;
    private Long forum_id;
    private Boolean obrisano = false;

    public ObavestenjeSaveDTO(Long id, String naslov, String tekstObavjestenja, Date vremePostavljanja, Long ulogovaniKorisnik_id, Long forum_id, Boolean obrisano) {
        this.id = id;
        this.naslov = naslov;
        this.tekstObavjestenja = tekstObavjestenja;
        this.vremePostavljanja = vremePostavljanja;
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
        this.forum_id = forum_id;
        this.obrisano = obrisano;
    }

    public ObavestenjeSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekstObavjestenja() {
        return tekstObavjestenja;
    }

    public void setTekstObavjestenja(String tekstObavjestenja) {
        this.tekstObavjestenja = tekstObavjestenja;
    }

    public Date getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(Date vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
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

    public Obavestenje toEntity() {
        Obavestenje e = new Obavestenje();
        e.setId(id);
        e.setNaslov(naslov);
        e.setTekstObavjestenja(tekstObavjestenja);
        e.setVremePostavljanja(vremePostavljanja);
        e.setObrisano(obrisano);
        return e;
    }
}
