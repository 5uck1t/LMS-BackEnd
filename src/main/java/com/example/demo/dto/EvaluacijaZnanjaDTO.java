package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.EvaluacijaZnanja;
import com.example.demo.saveDto.EvaluacijaZnanjaSaveDTO;

public class EvaluacijaZnanjaDTO {

    private Long id;
    private String naziv;
    private Float brojBodova;
    private Date datum;
    private PohadjanjePredmetaDTO pohadjanjepredmeta;
    private RokDTO rok;
    private Boolean obrisano = false;

    public EvaluacijaZnanjaDTO() {
    }

    public EvaluacijaZnanjaDTO(Long id, String naziv, Float brojBodova, Date datum, PohadjanjePredmetaDTO pohadjanjepredmeta, RokDTO rok, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.brojBodova = brojBodova;
        this.datum = datum;
        this.pohadjanjepredmeta = pohadjanjepredmeta;
        this.rok = rok;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Float getBrojBodova() { return brojBodova; }
    public void setBrojBodova(Float brojBodova) { this.brojBodova = brojBodova; }

    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }

    public PohadjanjePredmetaDTO getPohadjanjepredmeta() { return pohadjanjepredmeta; }
    public void setPohadjanjepredmeta(PohadjanjePredmetaDTO pohadjanjepredmeta) { this.pohadjanjepredmeta = pohadjanjepredmeta; }

    public RokDTO getRok() { return rok; }
    public void setRok(RokDTO rok) { this.rok = rok; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public EvaluacijaZnanja toEntity() {
        EvaluacijaZnanja e = new EvaluacijaZnanja();
        e.setId(id);
        e.setNaziv(naziv);
        e.setBrojBodova(brojBodova);
        e.setDatum(datum);
        e.setPohadjanjepredmeta(pohadjanjepredmeta.toEntity());
        e.setRok(rok.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public EvaluacijaZnanjaSaveDTO toSaveDto() {
        EvaluacijaZnanjaSaveDTO e = new EvaluacijaZnanjaSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setBrojBodova(brojBodova);
        e.setDatum(datum);
        e.setPohadjanjepredmeta_id(pohadjanjepredmeta.getId());
        e.setRok_id(rok.getId());
        e.setObrisano(obrisano);
        return e;
    }

}