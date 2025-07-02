package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Adresa;
import com.example.demo.saveDto.AdresaSaveDTO;

public class AdresaDTO {

    private Long id;
    private String ulica;
    private String broj;
    private MestoDTO mesto;
    private Set<UniverzitetDTO> univerziteti;
    private Set<OsobaDTO> osobe;
    private Boolean obrisano = false;

    public AdresaDTO() {
    }

    public AdresaDTO(Long id, String ulica, String broj, MestoDTO mesto, Set<UniverzitetDTO> univerziteti, Set<OsobaDTO> osobe, Boolean obrisano) {
        this.id = id;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.univerziteti = univerziteti;
        this.osobe = osobe;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUlica() { return ulica; }
    public void setUlica(String ulica) { this.ulica = ulica; }

    public String getBroj() { return broj; }
    public void setBroj(String broj) { this.broj = broj; }

    public MestoDTO getMesto() { return mesto; }
    public void setMesto(MestoDTO mesto) { this.mesto = mesto; }

    public Set<UniverzitetDTO> getUniverziteti() { return univerziteti; }
    public void setUniverziteti(Set<UniverzitetDTO> univerziteti) { this.univerziteti = univerziteti; }

    public Set<OsobaDTO> getOsobe() { return osobe; }
    public void setOsobe(Set<OsobaDTO> osobe) { this.osobe = osobe; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Adresa toEntity() {
        Adresa e = new Adresa();
        e.setId(id);
        e.setUlica(ulica);
        e.setBroj(broj);
        e.setMesto(mesto.toEntity());
        e.setUniverziteti(null);
        e.setOsobe(null);
        e.setObrisano(obrisano);
        return e;
    }

    public AdresaSaveDTO toSaveDto() {
        AdresaSaveDTO e = new AdresaSaveDTO();
        e.setId(id);
        e.setUlica(ulica);
        e.setBroj(broj);
        e.setMesto_id(mesto.getId());
        e.setObrisano(obrisano);
        return e;
    }

}