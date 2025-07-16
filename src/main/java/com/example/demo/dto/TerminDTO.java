package com.example.demo.dto;

import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Termin;
import com.example.demo.model.TipZvanja;
import com.example.demo.saveDto.TerminSaveDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TerminDTO {

    private Long id;

    private LocalDate datum;

    private LocalTime vremePocetka;

    private LocalTime vremeKraja;

    private RealizacijaPredmetaDTO realizacijaPredmeta;

    private Boolean obrisano;

    public TerminDTO(Long id, LocalDate datum, LocalTime vremePocetka, LocalTime vremeKraja, RealizacijaPredmetaDTO realizacijaPredmeta, Boolean obrisano) {
        this.id = id;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.obrisano = obrisano;
    }

    public TerminDTO() {
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

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Termin toEntity() {
        Termin e = new Termin();
        e.setId(id);
        e.setDatum(datum);
        e.setVremePocetka(vremePocetka);
        e.setVremeKraja(vremeKraja);
        e.setRealizacijaPredmeta(realizacijaPredmeta.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public TerminSaveDTO toSaveDto() {
        TerminSaveDTO e = new TerminSaveDTO();
        e.setId(id);
        e.setDatum(datum);
        e.setVremePocetka(vremePocetka);
        e.setVremeKraja(vremeKraja);
        e.setRealizacijaPredmeta_id(realizacijaPredmeta.getId());
        e.setObrisano(obrisano);
        return e;
    }
}
