package com.example.demo.model;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class EvaluacijaZnanja {
    @Id
    private Long id;

    private String naziv;

    private Float brojBodova;

    private Date datum;

    @ManyToOne
    private PohadjanjePredmeta pohadjanjepredmeta;

    @ManyToOne
    private Rok rok;

    @ColumnDefault("false")
    private Boolean obrisano;

    public EvaluacijaZnanja(Float brojBodova, Date datum, Long id, String naziv, Boolean obrisano, PohadjanjePredmeta pohadjanjepredmeta, Rok rok) {
        this.brojBodova = brojBodova;
        this.datum = datum;
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
        this.pohadjanjepredmeta = pohadjanjepredmeta;
        this.rok = rok;
    }

    public EvaluacijaZnanja() {
    }

    public Float getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Float brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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

    public PohadjanjePredmeta getPohadjanjepredmeta() {
        return pohadjanjepredmeta;
    }

    public void setPohadjanjepredmeta(PohadjanjePredmeta pohadjanjepredmeta) {
        this.pohadjanjepredmeta = pohadjanjepredmeta;
    }

    public Rok getRok() {
        return rok;
    }

    public void setRok(Rok rok) {
        this.rok = rok;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public EvaluacijaZnanjaDTO toDto() {
        return new EvaluacijaZnanjaDTO(this.id, this.naziv, this.brojBodova, this.datum, this.pohadjanjepredmeta.toDto(), this.rok.toDto(), this.obrisano);
    }
}
