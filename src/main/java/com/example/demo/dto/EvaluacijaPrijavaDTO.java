package com.example.demo.dto;

import java.util.Date;

public class EvaluacijaPrijavaDTO {
    private Long pohadjanjePredmetaId;
    private Long rokId;
    private String naziv;
    private Date datum;

    public EvaluacijaPrijavaDTO() {}

    public EvaluacijaPrijavaDTO(Long pohadjanjePredmetaId, Long rokId, String naziv, Date datum) {
        this.pohadjanjePredmetaId = pohadjanjePredmetaId;
        this.rokId = rokId;
        this.naziv = naziv;
        this.datum = datum;
    }

    public Long getPohadjanjePredmetaId() {
        return pohadjanjePredmetaId;
    }

    public void setPohadjanjePredmetaId(Long pohadjanjePredmetaId) {
        this.pohadjanjePredmetaId = pohadjanjePredmetaId;
    }

    public Long getRokId() {
        return rokId;
    }

    public void setRokId(Long rokId) {
        this.rokId = rokId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
