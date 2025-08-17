package com.example.demo.model;

import com.example.demo.dto.NastavnikDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Nastavnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String biografija;

    @OneToOne
    private Osoba osoba;

    @OneToMany(mappedBy = "nastavnik")
    private Set<RealizacijaPredmeta> realizacijePredmeta;

    @OneToOne(mappedBy = "dekan")
    private Fakultet fakultet;

    @OneToMany(mappedBy = "sefKatedre")
    private Set<Katedra> katedre;

    @OneToMany(mappedBy = "nastavnik")
    private Set<NastavnikHasZvanje> nastavnikHasZvanje;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Nastavnik(String biografija, Fakultet fakultet, Long id, Set<Katedra> katedre, Set<NastavnikHasZvanje> nastavnikHasZvanje,Set<RealizacijaPredmeta> realizacijePredmeta ,Boolean obrisano, Osoba osoba) {
        this.biografija = biografija;
        this.fakultet = fakultet;
        this.id = id;
        this.katedre = katedre;
        this.nastavnikHasZvanje = nastavnikHasZvanje;
        this.realizacijePredmeta = realizacijePredmeta;
        this.obrisano = obrisano;
        this.osoba = osoba;
    }

    public Nastavnik() {
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Katedra> getKatedre() {
        return katedre;
    }

    public void setKatedre(Set<Katedra> katedre) {
        this.katedre = katedre;
    }

    public Set<NastavnikHasZvanje> getNastavnikHasZvanje() {
        return nastavnikHasZvanje;
    }

    public void setNastavnikHasZvanje(Set<NastavnikHasZvanje> nastavnikHasZvanje) {
        this.nastavnikHasZvanje = nastavnikHasZvanje;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public NastavnikDTO toDto() {
        return new NastavnikDTO(this.id, this.biografija, this.osoba.toDto(), null, null, null, null, this.obrisano);
    }
}
