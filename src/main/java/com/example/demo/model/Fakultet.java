package com.example.demo.model;

import com.example.demo.dto.FakultetDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Fakultet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    private Univerzitet univerzitet;

    @OneToOne
    private Nastavnik dekan;

    @OneToMany(mappedBy = "fakultet")
    private Set<Katedra> katedre;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Fakultet(Nastavnik dekan, Long id, Set<Katedra> katedre, String naziv, Boolean obrisano, Univerzitet univerzitet) {
        this.dekan = dekan;
        this.id = id;
        this.katedre = katedre;
        this.naziv = naziv;
        this.obrisano = obrisano;
        this.univerzitet = univerzitet;
    }

    public Fakultet() {
    }

    public Nastavnik getDekan() {
        return dekan;
    }

    public void setDekan(Nastavnik dekan) {
        this.dekan = dekan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Katedra> getKatedre() {
        return katedre;
    }

    public void setKatedre(Set<Katedra> katedre) {
        this.katedre = katedre;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Univerzitet getUniverzitet() {
        return univerzitet;
    }

    public void setUniverzitet(Univerzitet univerzitet) {
        this.univerzitet = univerzitet;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public FakultetDTO toDto(){
        return new FakultetDTO(this.id, this.naziv, this.univerzitet.toDto(), this.dekan.toDto(), null, this.obrisano);
    }
}
