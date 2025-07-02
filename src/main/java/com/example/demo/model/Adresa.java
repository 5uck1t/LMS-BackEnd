package com.example.demo.model;

import com.example.demo.dto.AdresaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ulica;

    private String broj;

    @ManyToOne
    private Mesto mesto;

    @OneToMany(mappedBy = "adresa")
    private Set<Univerzitet> univerziteti;

    @OneToMany(mappedBy = "adresa")
    private Set<Osoba> osobe;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Adresa(String broj, Long id, Mesto mesto, Boolean obrisano, Set<Osoba> osobe, String ulica, Set<Univerzitet> univerziteti) {
        this.broj = broj;
        this.id = id;
        this.mesto = mesto;
        this.obrisano = obrisano;
        this.osobe = osobe;
        this.ulica = ulica;
        this.univerziteti = univerziteti;
    }

    public Adresa() {
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public Set<Univerzitet> getUniverziteti() {
        return univerziteti;
    }

    public void setUniverziteti(Set<Univerzitet> univerziteti) {
        this.univerziteti = univerziteti;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Set<Osoba> getOsobe() {
        return osobe;
    }

    public void setOsobe(Set<Osoba> osobe) {
        this.osobe = osobe;
    }

    public AdresaDTO toDto() {
        return new AdresaDTO(this.id, this.ulica, this.broj, this.mesto.toDto(), null, null, this.obrisano);
    }
}
