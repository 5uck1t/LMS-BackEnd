package com.example.demo.model;

import com.example.demo.dto.ObavestenjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
public class Obavestenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naslov;

    private String tekstObavjestenja;

    private Date vremePostavljanja;

    @ManyToOne
    private UlogovaniKorisnik korisnik;

    @ManyToOne
    private Forum forum;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Obavestenje(Forum forum, Long id, UlogovaniKorisnik korisnik, String naslov, Boolean obrisano, String tekstObavjestenja, Date vremePostavljanja) {
        this.forum = forum;
        this.id = id;
        this.korisnik = korisnik;
        this.naslov = naslov;
        this.obrisano = obrisano;
        this.tekstObavjestenja = tekstObavjestenja;
        this.vremePostavljanja = vremePostavljanja;
    }

    public Obavestenje() {
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
    public UlogovaniKorisnik getKorisnik() {
        return korisnik;
    }
    public void setKorisnik(UlogovaniKorisnik korisnik) {
        this.korisnik = korisnik;
    }
    public Forum getForum() {
        return forum;
    }
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public ObavestenjeDTO toDto() {
        return new ObavestenjeDTO(this.id, this.naslov, this.tekstObavjestenja, this.vremePostavljanja, this.korisnik.toDto(), this.forum.toDto(), this.obrisano);
    }
}
