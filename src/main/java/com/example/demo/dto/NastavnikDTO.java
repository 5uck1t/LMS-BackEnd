package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Nastavnik;
import com.example.demo.saveDto.NastavnikSaveDTO;

public class NastavnikDTO {

    private Long id;
    private String biografija;
    private OsobaDTO osoba;
    private FakultetDTO fakultet;
    private Set<KatedraDTO> katedre;
    private Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje;
    private Set<RealizacijaPredmetaDTO> realizacijePredmeta;
    private Boolean obrisano = false;

    public NastavnikDTO() {
    }

    public NastavnikDTO(Long id, String biografija, OsobaDTO osoba, FakultetDTO fakultet, Set<KatedraDTO> katedre, Set<RealizacijaPredmetaDTO> realizacijePredmeta, Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje, Boolean obrisano) {
        this.id = id;
        this.biografija = biografija;
        this.osoba = osoba;
        this.fakultet = fakultet;
        this.katedre = katedre;
        this.nastavnikHasZvanje = nastavnikHasZvanje;
        this.realizacijePredmeta = realizacijePredmeta;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBiografija() { return biografija; }
    public void setBiografija(String biografija) { this.biografija = biografija; }

    public OsobaDTO getOsoba() { return osoba; }
    public void setOsoba(OsobaDTO osoba) { this.osoba = osoba; }

    public FakultetDTO getFakultet() { return fakultet; }
    public void setFakultet(FakultetDTO fakultet) { this.fakultet = fakultet; }

    public Set<KatedraDTO> getKatedre() { return katedre; }
    public void setKatedre(Set<KatedraDTO> katedre) { this.katedre = katedre; }

    public Set<NastavnikHasZvanjeDTO> getNastavnikHasZvanje() { return nastavnikHasZvanje; }
    public void setNastavnikHasZvanje(Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje) { this.nastavnikHasZvanje = nastavnikHasZvanje; }

    public Set<RealizacijaPredmetaDTO> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmetaDTO> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Nastavnik toEntity() {
        Nastavnik e = new Nastavnik();
        e.setId(id);
        e.setBiografija(biografija);
        e.setOsoba(osoba.toEntity());
        e.setFakultet(fakultet.toEntity());
        e.setKatedre(null);
        e.setNastavnikHasZvanje(null);
        e.setRealizacijePredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }

    public NastavnikSaveDTO toSaveDto() {
        NastavnikSaveDTO e = new NastavnikSaveDTO();
        e.setId(id);
        e.setBiografija(biografija);
        e.setOsoba_id(osoba.getId());
        e.setFakultet_id(fakultet.getId());
        e.setObrisano(obrisano);
        return e;
    }

}