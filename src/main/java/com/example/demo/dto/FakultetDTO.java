package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Fakultet;
import com.example.demo.saveDto.FakultetSaveDTO;

public class FakultetDTO {

    private Long id;
    private String naziv;
    private UniverzitetDTO univerzitet;
    private NastavnikDTO dekan;
    private AdresaDTO adresa;
    private Set<KatedraDTO> katedre;
    private Boolean obrisano = false;

    public FakultetDTO() {
    }

    public FakultetDTO(Long id, String naziv, UniverzitetDTO univerzitet, NastavnikDTO dekan, Set<KatedraDTO> katedre, Boolean obrisano, AdresaDTO adresa) {
        this.id = id;
        this.naziv = naziv;
        this.univerzitet = univerzitet;
        this.dekan = dekan;
        this.katedre = katedre;
        this.obrisano = obrisano;
        this.adresa = adresa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public UniverzitetDTO getUniverzitet() { return univerzitet; }
    public void setUniverzitet(UniverzitetDTO univerzitet) { this.univerzitet = univerzitet; }

    public NastavnikDTO getDekan() { return dekan; }
    public void setDekan(NastavnikDTO dekan) { this.dekan = dekan; }

    public Set<KatedraDTO> getKatedre() { return katedre; }
    public void setKatedre(Set<KatedraDTO> katedre) { this.katedre = katedre; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public AdresaDTO getAdresa() {
        return adresa;
    }

    public void setAdresa(AdresaDTO adresa) {
        this.adresa = adresa;
    }

    public Fakultet toEntity() {
        Fakultet e = new Fakultet();
        e.setId(id);
        e.setNaziv(naziv);
        e.setUniverzitet(univerzitet.toEntity());
        e.setDekan(dekan.toEntity());
        e.setKatedre(null);
        e.setObrisano(obrisano);
        e.setAdresa(adresa.toEntity());
        return e;
    }

    public FakultetSaveDTO toSaveDto() {
        FakultetSaveDTO e = new FakultetSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setUniverzitet_id(univerzitet.getId());
        e.setAdresa_id(adresa.getId());
        e.setDekan_id(dekan.getId());
        e.setObrisano(obrisano);
        return e;
    }

}