package com.example.demo.saveDto;

import com.example.demo.dto.MestoDTO;
import com.example.demo.dto.OsobaDTO;
import com.example.demo.dto.UniverzitetDTO;
import com.example.demo.model.Adresa;

import java.util.Set;

public class AdresaSaveDTO {

    private Long id;
    private String ulica;
    private String broj;
    private Long mesto_id;
    private Boolean obrisano = false;

    public AdresaSaveDTO(Long id, String ulica, String broj, Long mesto_id, Boolean obrisano) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto_id = mesto_id;
        this.obrisano = obrisano;
    }

    public AdresaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Long getMesto_id() {
        return mesto_id;
    }

    public void setMesto_id(Long mesto_id) {
        this.mesto_id = mesto_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Adresa toEntity() {
        Adresa e = new Adresa();
        e.setId(id);
        e.setUlica(ulica);
        e.setBroj(broj);
        e.setObrisano(obrisano);
        return e;
    }
}
