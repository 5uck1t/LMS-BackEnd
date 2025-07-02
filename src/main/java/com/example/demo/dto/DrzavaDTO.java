package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Drzava;
import com.example.demo.saveDto.DrzavaSaveDTO;

public class DrzavaDTO {

    private Long id;
    private String naziv;
    private Set<MestoDTO> mesta;
    private Boolean obrisano = false;

    public DrzavaDTO() {
    }

    public DrzavaDTO(Long id, String naziv, Set<MestoDTO> mesta, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.mesta = mesta;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Set<MestoDTO> getMesta() { return mesta; }
    public void setMesta(Set<MestoDTO> mesta) { this.mesta = mesta; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Drzava toEntity() {
        Drzava e = new Drzava();
        e.setId(id);
        e.setNaziv(naziv);
        e.setMesta(null);
        e.setObrisano(obrisano);
        return e;
    }

    public DrzavaSaveDTO toSaveDto() {
        DrzavaSaveDTO e = new DrzavaSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setObrisano(obrisano);
        return e;
    }

}