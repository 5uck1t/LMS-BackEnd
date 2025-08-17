package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.TipZvanja;
import com.example.demo.saveDto.TipZvanjaSaveDTO;

public class TipZvanjaDTO {

    private Long id;
    private String naziv;
    private Set<ZvanjeDTO> zvanje;
    private Boolean obrisano = false;

    public TipZvanjaDTO() {
    }

    public TipZvanjaDTO(Long id, String naziv, Set<ZvanjeDTO> zvanje, Boolean obrisano) {
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

    public TipZvanja toEntity() {
        TipZvanja e = new TipZvanja();
        e.setId(id);
        e.setNaziv(naziv);
        e.setZvanje(null);
        e.setObrisano(obrisano);
        return e;
    }

    public TipZvanjaSaveDTO toSaveDto() {
        TipZvanjaSaveDTO e = new TipZvanjaSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setObrisano(obrisano);
        return e;
    }

}