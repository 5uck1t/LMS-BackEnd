package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.PravoPristupa;
import com.example.demo.saveDto.PravoPristupaSaveDTO;

public class PravoPristupaDTO {

    private Long id;
    private String naziv;
    private Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa;
    private Boolean obrisano = false;

    public PravoPristupaDTO() {
    }

    public PravoPristupaDTO(Long id, String naziv, Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Set<DodeljenoPravoPristupa> getDodeljenaPravaPristupa() { return dodeljenaPravaPristupa; }
    public void setDodeljenaPravaPristupa(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa) { this.dodeljenaPravaPristupa = dodeljenaPravaPristupa; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public PravoPristupa toEntity() {
        PravoPristupa e = new PravoPristupa();
        e.setId(id);
        e.setNaziv(naziv);
        e.setDodeljenaPravaPristupa(null);
        e.setObrisano(obrisano);
        return e;
    }

    public PravoPristupaSaveDTO toSaveDto() {
        PravoPristupaSaveDTO e = new PravoPristupaSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setObrisano(obrisano);
        return e;
    }

}