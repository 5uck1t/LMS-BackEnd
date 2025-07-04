package com.example.demo.model;

import com.example.demo.dto.RealizacijaPredmetaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class RealizacijaPredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private GodinaStudija godinaStudija;

    @ManyToOne
    private Predmet predmet;

    @ManyToOne
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "realizacijaPredmeta")
    private Set<PohadjanjePredmeta> pohadjanjaPredmeta;

    @OneToMany(mappedBy = "realizacijaPredmeta")
    private Set<DatumPredmeta> datumiPredmeta;

    @ColumnDefault("false")
    private Boolean obrisano;

    public RealizacijaPredmeta(GodinaStudija godinaStudija, Long id, Boolean obrisano, Nastavnik nastavnik, Set<PohadjanjePredmeta> pohadjanjaPredmeta, Set<DatumPredmeta> datumiPredmeta, Predmet predmet) {
        this.godinaStudija = godinaStudija;
        this.id = id;
        this.obrisano = obrisano;
        this.nastavnik = nastavnik;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
        this.datumiPredmeta = datumiPredmeta;
        this.predmet = predmet;
    }

    public RealizacijaPredmeta() {
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PohadjanjePredmeta> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Set<DatumPredmeta> getDatumiPredmeta() {
        return datumiPredmeta;
    }

    public void setDatumiPredmeta(Set<DatumPredmeta> datumiPredmeta) {
        this.datumiPredmeta = datumiPredmeta;
    }

    public RealizacijaPredmetaDTO toDto() {
        return new RealizacijaPredmetaDTO(this.id, this.godinaStudija.toDto(), this.predmet.toDto(), this.nastavnik.toDto(), null, null, this.obrisano);
    }
}