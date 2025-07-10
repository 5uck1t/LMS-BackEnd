package com.example.demo.saveDto;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.UlogovaniKorisnik;

import java.util.Set;

public class UlogovaniKorisnikSaveDTO {

    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    private Boolean obrisano = false;
    private Long osoba_id;

    public UlogovaniKorisnikSaveDTO(Long id, String korisnickoIme, String lozinka, String email, Boolean obrisano, Long osoba_id) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.obrisano = obrisano;
        this.osoba_id = osoba_id;
    }

    public UlogovaniKorisnikSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Long getOsoba_id() {
        return osoba_id;
    }

    public void setOsoba_id(Long osoba_id) {
        this.osoba_id = osoba_id;
    }

    public UlogovaniKorisnik toEntity() {
        UlogovaniKorisnik e = new UlogovaniKorisnik();
        e.setId(id);
        e.setUsername(korisnickoIme);
        e.setPassword(lozinka);
        e.setEmail(email);
        e.setObrisano(obrisano);
        return e;
    }
}
