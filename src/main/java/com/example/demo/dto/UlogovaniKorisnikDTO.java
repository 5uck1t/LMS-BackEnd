package com.example.demo.dto;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.UlogovaniKorisnik;
import com.example.demo.saveDto.UlogovaniKorisnikSaveDTO;

import java.util.Set;

public class UlogovaniKorisnikDTO {

    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private String email;
    private Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa;
    private Boolean obrisano = false;
    private OsobaDTO osoba;

    public UlogovaniKorisnikDTO() {
    }

    public UlogovaniKorisnikDTO(Long id, String korisnickoIme, String lozinka, String email, Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa, Boolean obrisano, OsobaDTO osoba) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
        this.obrisano = obrisano;
        this.osoba = osoba;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKorisnickoIme() { return korisnickoIme; }
    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }

    public String getLozinka() { return lozinka; }
    public void setLozinka(String lozinka) { this.lozinka = lozinka; }

    public Set<DodeljenoPravoPristupa> getDodeljenaPravaPristupa() { return dodeljenaPravaPristupa; }
    public void setDodeljenaPravaPristupa(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa) { this.dodeljenaPravaPristupa = dodeljenaPravaPristupa; }

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

    public OsobaDTO getOsoba() {
        return osoba;
    }

    public void setOsoba(OsobaDTO osoba) {
        this.osoba = osoba;
    }

    public UlogovaniKorisnik toEntity() {
        UlogovaniKorisnik e = new UlogovaniKorisnik();
        e.setId(id);
        e.setUsername(korisnickoIme);
        e.setPassword(lozinka);
        e.setEmail(email);
        e.setDodeljenaPravaPristupa(dodeljenaPravaPristupa);
        return e;
    }

    public UlogovaniKorisnikSaveDTO toSaveDto() {
        UlogovaniKorisnikSaveDTO e = new UlogovaniKorisnikSaveDTO();
        e.setId(id);
        e.setKorisnickoIme(korisnickoIme);
        e.setLozinka(lozinka);
        e.setEmail(email);
        e.setOsoba_id(this.osoba.getId());
        return e;
    }
}
