package com.example.demo.model;

import com.example.demo.dto.StudijskiProgramDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Set;

@Entity
public class StudijskiProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    private Katedra katedra;

    @OneToMany(mappedBy = "studijskiProgram")
    private Set<GodinaStudija> godineStudija;

    @ColumnDefault("false")
    private Boolean obrisano;

    public StudijskiProgram(Set<GodinaStudija> godineStudija, Long id, Katedra katedra, String naziv, Boolean obrisano) {
        this.godineStudija = godineStudija;
        this.id = id;
        this.katedra = katedra;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public StudijskiProgram() {
    }

    public Set<GodinaStudija> getGodineStudija() {
        return godineStudija;
    }

    public void setGodineStudija(Set<GodinaStudija> godineStudija) {
        this.godineStudija = godineStudija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Katedra getKatedra() {
        return katedra;
    }

    public void setKatedra(Katedra katedra) {
        this.katedra = katedra;
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

    public StudijskiProgramDTO toDto() {
        return new StudijskiProgramDTO(this.id, this.naziv, this.katedra.toDto(), null, this.obrisano);
    }
}
