package com.example.demo.model;

import com.example.demo.dto.MestoDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Mesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Drzava drzava;

    @OneToMany(mappedBy = "mesto")
    private Set<Adresa> adrese;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Mesto(Set<Adresa> adrese, Drzava drzava, Long id, String naziv, Boolean obrisano) {
        this.adrese = adrese;
        this.drzava = drzava;
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public Mesto() {
    }

    public Set<Adresa> getAdrese() {
        return adrese;
    }

    public void setAdrese(Set<Adresa> adrese) {
        this.adrese = adrese;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public MestoDTO toDto() {
        return new MestoDTO(this.id, this.naziv, this.drzava.toDto(), null, this.obrisano);
    }
}

