package com.example.demo.model;

import com.example.demo.dto.NastavnikHasZvanjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class NastavnikHasZvanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Nastavnik nastavnik;

    @ManyToOne
    private Zvanje zvanje;

    @ColumnDefault("false")
    private Boolean obrisano;

    public NastavnikHasZvanje(Long id, Nastavnik nastavnik, Boolean obrisano, Zvanje zvanje) {
        this.id = id;
        this.nastavnik = nastavnik;
        this.obrisano = obrisano;
        this.zvanje = zvanje;
    }

    public NastavnikHasZvanje() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Zvanje getZvanje() {
        return zvanje;
    }

    public void setZvanje(Zvanje zvanje) {
        this.zvanje = zvanje;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public NastavnikHasZvanjeDTO toDto() {
        return new NastavnikHasZvanjeDTO(this.id, this.nastavnik.toDto(), this.zvanje.toDto(), this.obrisano);
    }
}
