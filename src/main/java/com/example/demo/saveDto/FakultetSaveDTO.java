package com.example.demo.saveDto;

import com.example.demo.dto.KatedraDTO;
import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.UniverzitetDTO;
import com.example.demo.model.Fakultet;

import java.util.Set;

public class FakultetSaveDTO {

    private Long id;
    private String naziv;
    private Long univerzitet_id;
    private Long adresa_id;
    private Long dekan_id;
    private Boolean obrisano = false;

    public FakultetSaveDTO(Long id, String naziv, Long univerzitet_id, Long dekan_id, Boolean obrisano, Long adresa_id) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet_id = univerzitet_id;
        this.adresa_id = adresa_id;
        this.dekan_id = dekan_id;
        this.obrisano = obrisano;
    }

    public FakultetSaveDTO() {
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

    public Long getUniverzitet_id() {
        return univerzitet_id;
    }

    public void setUniverzitet_id(Long univerzitet_id) {
        this.univerzitet_id = univerzitet_id;
    }

    public Long getDekan_id() {
        return dekan_id;
    }

    public void setDekan_id(Long dekan_id) {
        this.dekan_id = dekan_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Long getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Long adresa_id) {
        this.adresa_id = adresa_id;
    }

    public Fakultet toEntity() {
        Fakultet e = new Fakultet();
        e.setId(id);
        e.setNaziv(naziv);
        e.setKatedre(null);
        e.setObrisano(obrisano);
        return e;
    }
}
