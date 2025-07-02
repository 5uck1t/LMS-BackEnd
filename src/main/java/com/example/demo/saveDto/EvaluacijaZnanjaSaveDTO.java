package com.example.demo.saveDto;

import com.example.demo.dto.PohadjanjePredmetaDTO;
import com.example.demo.dto.RokDTO;
import com.example.demo.model.EvaluacijaZnanja;

import java.util.Date;

public class EvaluacijaZnanjaSaveDTO {
    private Long id;
    private String naziv;
    private Float brojBodova;
    private Date datum;
    private Long pohadjanjepredmeta_id;
    private Long rok_id;
    private Boolean obrisano = false;

    public EvaluacijaZnanjaSaveDTO(Long id, String naziv, Float brojBodova, Date datum, Long pohadjanjepredmeta_id, Long rok_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.brojBodova = brojBodova;
        this.datum = datum;
        this.pohadjanjepredmeta_id = pohadjanjepredmeta_id;
        this.rok_id = rok_id;
        this.obrisano = obrisano;
    }

    public EvaluacijaZnanjaSaveDTO() {
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

    public Long getPohadjanjepredmeta_id() {
        return pohadjanjepredmeta_id;
    }

    public void setPohadjanjepredmeta_id(Long pohadjanjepredmeta_id) {
        this.pohadjanjepredmeta_id = pohadjanjepredmeta_id;
    }

    public Long getRok_id() {
        return rok_id;
    }

    public void setRok_id(Long rok_id) {
        this.rok_id = rok_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public EvaluacijaZnanja toEntity() {
        EvaluacijaZnanja e = new EvaluacijaZnanja();
        e.setId(id);
        e.setNaziv(naziv);
        e.setBrojBodova(brojBodova);
        e.setDatum(datum);
        e.setObrisano(obrisano);
        return e;
    }
}
