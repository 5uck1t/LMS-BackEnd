package com.example.demo.saveDto;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.PravoPristupa;

import java.util.Set;

public class PravoPristupaSaveDTO {

    private Long id;
    private String naziv;
    private Boolean obrisano = false;

    public PravoPristupaSaveDTO(Long id, String naziv, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public PravoPristupaSaveDTO() {
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

    public PravoPristupa toEntity() {
        PravoPristupa e = new PravoPristupa();
        e.setId(id);
        e.setNaziv(naziv);
        e.setDodeljenaPravaPristupa(null);
        e.setObrisano(obrisano);
        return e;
    }
}
