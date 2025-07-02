package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Obavestenje;
import com.example.demo.saveDto.ObavestenjeSaveDTO;

public class ObavestenjeDTO {

    private Long id;
    private String naslov;
    private String tekstObavjestenja;
    private Date vremePostavljanja;
    private UlogovaniKorisnikDTO korisnik;
    private ForumDTO forum;
    private Boolean obrisano = false;

    public ObavestenjeDTO() {
    }

    public ObavestenjeDTO(Long id, String naslov, String tekstObavjestenja, Date vremePostavljanja, UlogovaniKorisnikDTO korisnik, ForumDTO forum, Boolean obrisano) {
        this.id = id;
        this.naslov = naslov;
        this.tekstObavjestenja = tekstObavjestenja;
        this.vremePostavljanja = vremePostavljanja;
        this.korisnik = korisnik;
        this.forum = forum;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getTekstObavjestenja() { return tekstObavjestenja; }
    public void setTekstObavjestenja(String tekstObavjestenja) { this.tekstObavjestenja = tekstObavjestenja; }

    public Date getVremePostavljanja() { return vremePostavljanja; }
    public void setVremePostavljanja(Date vremePostavljanja) { this.vremePostavljanja = vremePostavljanja; }

    public UlogovaniKorisnikDTO getKorisnik() { return korisnik; }
    public void setKorisnik(UlogovaniKorisnikDTO korisnik) { this.korisnik = korisnik; }

    public ForumDTO getForum() { return forum; }
    public void setForum(ForumDTO forum) { this.forum = forum; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Obavestenje toEntity() {
        Obavestenje e = new Obavestenje();
        e.setId(id);
        e.setNaslov(naslov);
        e.setTekstObavjestenja(tekstObavjestenja);
        e.setVremePostavljanja(vremePostavljanja);
        e.setKorisnik(korisnik.toEntity());
        e.setForum(forum.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public ObavestenjeSaveDTO toSaveDto() {
        ObavestenjeSaveDTO e = new ObavestenjeSaveDTO();
        e.setId(id);
        e.setNaslov(naslov);
        e.setTekstObavjestenja(tekstObavjestenja);
        e.setVremePostavljanja(vremePostavljanja);
        e.setUlogovaniKorisnik_id(korisnik.getId());
        e.setForum_id(forum.getId());
        e.setObrisano(obrisano);
        return e;
    }

}