package com.example.demo.saveDto;

import com.example.demo.dto.MestoDTO;
import com.example.demo.model.Drzava;

import java.util.Set;

public class DrzavaSaveDTO {

    private Long id;
    private String naziv;
    private Boolean obrisano = false;

    public DrzavaSaveDTO(Long id, String naziv, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public DrzavaSaveDTO() {
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

    public Drzava toEntity() {
        Drzava e = new Drzava();
        e.setId(id);
        e.setNaziv(naziv);
        e.setMesta(null);
        e.setObrisano(obrisano);
        return e;
    }
}
