package com.example.demo.model;

import com.example.demo.dto.PrijavaPolaganjaDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class PrijavaPolaganja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PohadjanjePredmeta pohadjanjePredmeta;

    @ManyToOne
    private Polaganje polaganje;

    private Date datum = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());;

    private Double brojBodova;

    private Boolean obrisano;

    public PrijavaPolaganja(Long id, PohadjanjePredmeta pohadjanjePredmeta, Polaganje polaganje, Date datum, Double brojBodova, Boolean obrisano) {
        this.id = id;
        this.pohadjanjePredmeta = pohadjanjePredmeta;
        this.polaganje = polaganje;
        this.datum = datum;
        this.brojBodova = brojBodova;
        this.obrisano = obrisano;
    }

    public PrijavaPolaganja() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PohadjanjePredmeta getPohadjanjePredmeta() {
        return pohadjanjePredmeta;
    }

    public void setPohadjanjePredmeta(PohadjanjePredmeta pohadjanjePredmeta) {
        this.pohadjanjePredmeta = pohadjanjePredmeta;
    }

    public Polaganje getPolaganje() {
        return polaganje;
    }

    public void setPolaganje(Polaganje polaganje) {
        this.polaganje = polaganje;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Double brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PrijavaPolaganjaDTO toDto(){
        PrijavaPolaganjaDTO e = new PrijavaPolaganjaDTO();

        e.setId(id);
        e.setBrojBodova(brojBodova);
        e.setObrisano(obrisano);
        e.setDatum(datum);
        e.setPolaganje(polaganje.toDto());
        e.setPohadjanjePredmeta(pohadjanjePredmeta.toDto());

        return e;
    }
}
