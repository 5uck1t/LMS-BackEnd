package com.example.demo.model;

import com.example.demo.dto.UlogovaniKorisnikDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class UlogovaniKorisnik {
    @Id
    private Long id;

    private String username;

    private String password;

    private String email;

    @OneToOne
    private Osoba osoba;

    @OneToMany(mappedBy = "korisnik")
    private Set<Obavestenje> obavestenja;

    @OneToMany(mappedBy = "ulogovaniKorisnik")
    private Set<ForumHasKorisnik> forumHasKorisnik;

    @OneToMany(mappedBy = "ulogovaniKorisnik")
    private Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa;

    @ColumnDefault("false")
    private Boolean obrisano;

    public UlogovaniKorisnik(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa, String email, Set<ForumHasKorisnik> forumHasKorisnik, Long id, Set<Obavestenje> obavestenja, Boolean obrisano, Osoba osoba, String password, String username) {
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
        this.email = email;
        this.forumHasKorisnik = forumHasKorisnik;
        this.id = id;
        this.obavestenja = obavestenja;
        this.obrisano = obrisano;
        this.osoba = osoba;
        this.password = password;
        this.username = username;
    }

    public UlogovaniKorisnik() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Set<DodeljenoPravoPristupa> getDodeljenaPravaPristupa() {
        return dodeljenaPravaPristupa;
    }

    public void setDodeljenaPravaPristupa(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa) {
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
    }

    public Set<ForumHasKorisnik> getForumHasKorisnik() {
        return forumHasKorisnik;
    }

    public void setForumHasKorisnik(Set<ForumHasKorisnik> forumHasKorisnik) {
        this.forumHasKorisnik = forumHasKorisnik;
    }

    public Set<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(Set<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public UlogovaniKorisnikDTO toDto() {
        return new UlogovaniKorisnikDTO(this.id, this.username, this.password,this.email, null, this.obrisano, this.osoba.toDto());
    }
}
