package com.example.demo.model;

import com.example.demo.dto.OdgovorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Odgovor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String odgovor;

    @ManyToOne
    @JsonIgnore
    private Zadatak zadatak;

    private Boolean tacan;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Odgovor(Long id, String odgovor, Zadatak zadatak, Boolean obrisano, Boolean tacan) {
        this.id = id;
        this.odgovor = odgovor;
        this.zadatak = zadatak;
        this.obrisano = obrisano;
        this.tacan = tacan;
    }

    public Odgovor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public Zadatak getZadatak() {
        return zadatak;
    }

    public void setZadatak(Zadatak zadatak) {
        this.zadatak = zadatak;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Boolean getTacan() {
        return tacan;
    }

    public void setTacan(Boolean tacan) {
        this.tacan = tacan;
    }

    public OdgovorDTO toDto(){

        return new OdgovorDTO(this.id, this.odgovor, null, this.obrisano,this.tacan);
    }
}
