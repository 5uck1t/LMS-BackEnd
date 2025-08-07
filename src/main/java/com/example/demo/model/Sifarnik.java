package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Sifarnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String naziv;

    @Column(length = 1000)
    private String tekst;

    @ColumnDefault("false")
    private Boolean obrisano = false;

    public Sifarnik(long id, String naziv, String tekst, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.tekst = tekst;
        this.obrisano = obrisano;
    }

    public Sifarnik() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }
}
