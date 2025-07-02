package com.example.demo.saveDto;

import com.example.demo.dto.ZvanjeDTO;
import com.example.demo.model.NaucnaOblast;

import java.util.Set;

public class NaucnaOblastSaveDTO {

    private Long id;
    private String naziv;
    private Boolean obrisano = false;

    public NaucnaOblastSaveDTO(Long id, String naziv, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public NaucnaOblastSaveDTO() {
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

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public NaucnaOblast toEntity() {
        NaucnaOblast e = new NaucnaOblast();
        e.setId(id);
        e.setNaziv(naziv);
        e.setZvanje(null);
        e.setObrisano(obrisano);
        return e;
    }
}
