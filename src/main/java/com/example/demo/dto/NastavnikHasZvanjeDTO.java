package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.NastavnikHasZvanje;
import com.example.demo.saveDto.NastavnikHasZvanjeSaveDTO;

public class NastavnikHasZvanjeDTO {

    private Long id;
    private NastavnikDTO nastavnik;
    private ZvanjeDTO zvanje;
    private Boolean obrisano = false;

    public NastavnikHasZvanjeDTO() {
    }

    public NastavnikHasZvanjeDTO(Long id, NastavnikDTO nastavnik, ZvanjeDTO zvanje, Boolean obrisano) {
        this.id = id;
        this.nastavnik = nastavnik;
        this.zvanje = zvanje;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public NastavnikDTO getNastavnik() { return nastavnik; }
    public void setNastavnik(NastavnikDTO nastavnik) { this.nastavnik = nastavnik; }

    public ZvanjeDTO getZvanje() { return zvanje; }
    public void setZvanje(ZvanjeDTO zvanje) { this.zvanje = zvanje; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public NastavnikHasZvanje toEntity() {
        NastavnikHasZvanje e = new NastavnikHasZvanje();
        e.setId(id);
        e.setNastavnik(nastavnik.toEntity());
        e.setZvanje(zvanje.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public NastavnikHasZvanjeSaveDTO toSaveDto() {
        NastavnikHasZvanjeSaveDTO e = new NastavnikHasZvanjeSaveDTO();
        e.setId(id);
        e.setNastavnik_id(nastavnik.getId());
        e.setZvanje_id(zvanje.getId());
        e.setObrisano(obrisano);
        return e;
    }

}