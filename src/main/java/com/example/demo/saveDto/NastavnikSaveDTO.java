package com.example.demo.saveDto;

import com.example.demo.dto.*;
import com.example.demo.model.Nastavnik;

import java.util.Set;

public class NastavnikSaveDTO {

    private Long id;
    private String biografija;
    private Long osoba_id;
    private Boolean obrisano = false;

    public NastavnikSaveDTO(Long id, String biografija, Long osoba_id, Boolean obrisano) {
        this.id = id;
        this.biografija = biografija;
        this.osoba_id = osoba_id;
        this.obrisano = obrisano;
    }

    public NastavnikSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public Long getOsoba_id() {
        return osoba_id;
    }

    public void setOsoba_id(Long osoba_id) {
        this.osoba_id = osoba_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Nastavnik toEntity() {
        Nastavnik e = new Nastavnik();
        e.setId(id);
        e.setBiografija(biografija);
        e.setKatedre(null);
        e.setNastavnikHasZvanje(null);
        e.setRealizacijePredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }
}
