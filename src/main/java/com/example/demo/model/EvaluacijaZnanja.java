package com.example.demo.model;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.dto.PolaganjeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class EvaluacijaZnanja {
    @Id
    private Long id;

    private String naziv;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;

    @OneToMany(mappedBy = "evaluacijaZnanja")
    private Set<Zadatak> zadaci;

    @OneToMany(mappedBy = "evaluacijaZnanja")
    private Set<Polaganje> polaganja;

    @ColumnDefault("false")
    private Boolean obrisano;

    public EvaluacijaZnanja(Long id, String naziv, RealizacijaPredmeta realizacijaPredmeta, Set<Zadatak> zadaci, Set<Polaganje> polaganja, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.zadaci = zadaci;
        this.obrisano = obrisano;
        this.polaganja = polaganja;
    }

    public EvaluacijaZnanja() {
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

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Set<Zadatak> getZadaci() {
        return zadaci;
    }

    public void setZadaci(Set<Zadatak> zadaci) {
        this.zadaci = zadaci;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Set<Polaganje> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(Set<Polaganje> polaganja) {
        this.polaganja = polaganja;
    }

    public EvaluacijaZnanjaDTO toDto() {
        return new EvaluacijaZnanjaDTO(this.id, this.naziv, this.realizacijaPredmeta.toDto(), this.obrisano);
    }
}
