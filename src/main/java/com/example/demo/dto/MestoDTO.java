package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Mesto;
import com.example.demo.saveDto.MestoSaveDTO;

public class MestoDTO {

    private Long id;
    private String naziv;
    private DrzavaDTO drzava;
    private Set<AdresaDTO> adrese;
    private Boolean obrisano = false;

    public MestoDTO() {
    }

    public MestoDTO(Long id, String naziv, DrzavaDTO drzava, Set<AdresaDTO> adrese, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.adrese = adrese;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public DrzavaDTO getDrzava() { return drzava; }
    public void setDrzava(DrzavaDTO drzava) { this.drzava = drzava; }

    public Set<AdresaDTO> getAdrese() { return adrese; }
    public void setAdrese(Set<AdresaDTO> adrese) { this.adrese = adrese; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Mesto toEntity() {
        Mesto e = new Mesto();
        e.setId(id);
        e.setNaziv(naziv);
        e.setDrzava(drzava.toEntity());
        e.setAdrese(null);
        e.setObrisano(obrisano);
        return e;
    }

    public MestoSaveDTO toSaveDto() {
        MestoSaveDTO e = new MestoSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setDrzava_id(drzava.getId());
        e.setObrisano(obrisano);
        return e;
    }

}