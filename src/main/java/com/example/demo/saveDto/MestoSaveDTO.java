package com.example.demo.saveDto;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.dto.DrzavaDTO;
import com.example.demo.model.Mesto;

import java.util.Set;

public class MestoSaveDTO {

    private Long id;
    private String naziv;
    private Long drzava_id;
    private Boolean obrisano = false;

    public MestoSaveDTO(Long id, String naziv, Long drzava_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.drzava_id = drzava_id;
        this.obrisano = obrisano;
    }

    public MestoSaveDTO() {
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

    public Long getDrzava_id() {
        return drzava_id;
    }

    public void setDrzava_id(Long drzava_id) {
        this.drzava_id = drzava_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Mesto toEntity() {
        Mesto e = new Mesto();
        e.setId(id);
        e.setNaziv(naziv);
        e.setAdrese(null);
        e.setObrisano(obrisano);
        return e;
    }
}
