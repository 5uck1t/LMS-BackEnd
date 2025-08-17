package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.saveDto.RealizacijaPredmetaSaveDTO;

public class RealizacijaPredmetaDTO {

    private Long id;
    private GodinaStudijaDTO godinaStudija;
    private PredmetDTO predmet;
    private NastavnikDTO nastavnik;
    private Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta;
    private Set<DatumPredmetaDTO> datumiPredmeta;
    private Boolean obrisano = false;

    public RealizacijaPredmetaDTO() {
    }

    public RealizacijaPredmetaDTO(Long id, GodinaStudijaDTO godinaStudija, PredmetDTO predmet, NastavnikDTO nastavnik, Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta, Set<DatumPredmetaDTO> datumiPredmeta, Boolean obrisano) {
        this.id = id;
        this.godinaStudija = godinaStudija;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
        this.obrisano = obrisano;
        this.datumiPredmeta = datumiPredmeta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GodinaStudijaDTO getGodinaStudija() { return godinaStudija; }
    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) { this.godinaStudija = godinaStudija; }

    public PredmetDTO getPredmet() { return predmet; }
    public void setPredmet(PredmetDTO predmet) { this.predmet = predmet; }

    public NastavnikDTO getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(NastavnikDTO nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Set<PohadjanjePredmetaDTO> getPohadjanjaPredmeta() { return pohadjanjaPredmeta; }
    public void setPohadjanjaPredmeta(Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta) { this.pohadjanjaPredmeta = pohadjanjaPredmeta; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Set<DatumPredmetaDTO> getDatumiPredmeta() {return datumiPredmeta;}
    public void setDatumiPredmeta(Set<DatumPredmetaDTO> datumiPredmeta) {this.datumiPredmeta = datumiPredmeta;}

    public RealizacijaPredmeta toEntity() {
        RealizacijaPredmeta e = new RealizacijaPredmeta();
        e.setId(id);
        e.setGodinaStudija(godinaStudija.toEntity());
        e.setPredmet(predmet.toEntity());
        e.setPohadjanjaPredmeta(null);
        e.setObrisano(obrisano);
        e.setNastavnik(nastavnik.toEntity());
        e.setDatumiPredmeta(null);
        return e;
    }

    public RealizacijaPredmetaSaveDTO toSaveDto() {
        RealizacijaPredmetaSaveDTO e = new RealizacijaPredmetaSaveDTO();
        e.setId(id);
        e.setGodinaStudija_id(godinaStudija.getId());
        e.setPredmet_id(predmet.getId());
        e.setObrisano(obrisano);
        e.setNastavnik_id(nastavnik.getId());
        return e;
    }

}