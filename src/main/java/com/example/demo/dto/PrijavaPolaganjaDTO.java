package com.example.demo.dto;

import com.example.demo.model.EvaluacijaZnanja;
import com.example.demo.model.Polaganje;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.saveDto.PrijavaPolaganjaSaveDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PrijavaPolaganjaDTO {

    private Long id;

    private PohadjanjePredmetaDTO pohadjanjePredmeta;

    private PolaganjeDTO polaganje;

    private Date datum = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    private Double brojBodova;

    private Boolean obrisano;

    public PrijavaPolaganjaDTO(Long id, PohadjanjePredmetaDTO pohadjanjePredmeta, PolaganjeDTO polaganje, Date datum, Double brojBodova, Boolean obrisano) {
        this.id = id;
        this.pohadjanjePredmeta = pohadjanjePredmeta;
        this.polaganje = polaganje;
        this.datum = datum;
        this.brojBodova = brojBodova;
        this.obrisano = obrisano;
    }

    public PrijavaPolaganjaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PohadjanjePredmetaDTO getPohadjanjePredmeta() {
        return pohadjanjePredmeta;
    }

    public void setPohadjanjePredmeta(PohadjanjePredmetaDTO pohadjanjePredmeta) {
        this.pohadjanjePredmeta = pohadjanjePredmeta;
    }

    public PolaganjeDTO getPolaganje() {
        return polaganje;
    }

    public void setPolaganje(PolaganjeDTO polaganje) {
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

    public PrijavaPolaganja toEntity(){
        PrijavaPolaganja e = new PrijavaPolaganja();

        e.setId(id);
        e.setBrojBodova(brojBodova);
        e.setObrisano(obrisano);
        e.setDatum(datum);
        e.setPolaganje(polaganje.toEntity());
        e.setPohadjanjePredmeta(pohadjanjePredmeta.toEntity());

        return e;
    }

    public PrijavaPolaganjaSaveDTO toSaveDto(){

        PrijavaPolaganjaSaveDTO e = new PrijavaPolaganjaSaveDTO();

        e.setId(id);
        e.setBrojBodova(brojBodova);
        e.setObrisano(obrisano);
        e.setDatum(datum);
        e.setPolaganje_id(polaganje.getId());
        e.setPohadjanjePredmeta_id(pohadjanjePredmeta.getId());

        return e;
    }
}
