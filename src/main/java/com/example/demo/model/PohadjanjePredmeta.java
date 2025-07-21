package com.example.demo.model;

import com.example.demo.dto.PohadjanjePredmetaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class PohadjanjePredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int konacnaOcena;

    @ManyToOne
    private StudentNaGodini studentNaGodini;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;

    @OneToMany(mappedBy = "pohadjanjePredmeta")
    private Set<PrijavaPolaganja> prijave;

    @ColumnDefault("false")
    private Boolean obrisano;

    public PohadjanjePredmeta(Long id, int konacnaOcena, Boolean obrisano, RealizacijaPredmeta realizacijaPredmeta, StudentNaGodini studentNaGodini) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.obrisano = obrisano;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.studentNaGodini = studentNaGodini;
    }
    public PohadjanjePredmeta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(int konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public StudentNaGodini getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(StudentNaGodini studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PohadjanjePredmetaDTO toDto() {
        return new PohadjanjePredmetaDTO(this.id, this.konacnaOcena, this.studentNaGodini.toDto(), this.realizacijaPredmeta.toDto(), null, this.obrisano);
    }
}
