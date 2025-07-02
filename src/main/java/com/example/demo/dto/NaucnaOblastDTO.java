package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.NaucnaOblast;
import com.example.demo.saveDto.NaucnaOblastSaveDTO;

public class NaucnaOblastDTO {

    private Long id;
    private String naziv;
    private Set<ZvanjeDTO> zvanje;
    private Boolean obrisano = false;

    public NaucnaOblastDTO() {
    }

    public NaucnaOblastDTO(Long id, String naziv, Set<ZvanjeDTO> zvanje, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.zvanje = zvanje;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Set<ZvanjeDTO> getZvanje() { return zvanje; }
    public void setZvanje(Set<ZvanjeDTO> zvanje) { this.zvanje = zvanje; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public NaucnaOblast toEntity() {
        NaucnaOblast e = new NaucnaOblast();
        e.setId(id);
        e.setNaziv(naziv);
        e.setZvanje(null);
        e.setObrisano(obrisano);
        return e;
    }

    public NaucnaOblastSaveDTO toSaveDto() {
        NaucnaOblastSaveDTO e = new NaucnaOblastSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setObrisano(obrisano);
        return e;
    }

}