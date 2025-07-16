package com.example.demo.model;

import com.example.demo.dto.TerminDTO;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datum;

    private LocalTime vremePocetka;

    private LocalTime vremeKraja;

    private Boolean obrisano;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;

    public Termin(Long id, LocalDate datum, LocalTime vremePocetka, LocalTime vremeKraja, Boolean obrisano, RealizacijaPredmeta realizacijaPredmeta) {
        this.id = id;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.obrisano = obrisano;
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Termin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public TerminDTO toDto(){
        return new TerminDTO(this.id,this.datum,this.vremePocetka,this.vremeKraja,this.realizacijaPredmeta.toDto(),this.obrisano);
    }
}
