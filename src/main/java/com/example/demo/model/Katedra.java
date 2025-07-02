package com.example.demo.model;

import com.example.demo.dto.KatedraDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Katedra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    private Fakultet fakultet;

    @ManyToOne
    private Nastavnik sefKatedre;

    @OneToMany(mappedBy = "katedra")
    private Set<StudijskiProgram> studijskiProgrami;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Katedra(Fakultet fakultet, Long id, String naziv, Boolean obrisano, Nastavnik sefKatedre, Set<StudijskiProgram> studijskiProgrami) {
        this.fakultet = fakultet;
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
        this.sefKatedre = sefKatedre;
        this.studijskiProgrami = studijskiProgrami;
    }

    public Katedra() {
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
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

    public Nastavnik getSefKatedre() {
        return sefKatedre;
    }

    public void setSefKatedre(Nastavnik sefKatedre) {
        this.sefKatedre = sefKatedre;
    }

    public Set<StudijskiProgram> getStudijskiProgrami() {
        return studijskiProgrami;
    }

    public void setStudijskiProgrami(Set<StudijskiProgram> studijskiProgrami) {
        this.studijskiProgrami = studijskiProgrami;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public KatedraDTO toDto() {
        return new KatedraDTO(this.id, this.naziv, this.fakultet.toDto(), this.sefKatedre.toDto(), null, this.obrisano);
    }
}
