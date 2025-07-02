package com.example.demo.model;

import com.example.demo.dto.DrzavaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Drzava {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @OneToMany(mappedBy = "drzava")
    private Set<Mesto> mesta;

    private Boolean obrisano = false;

    public Drzava(Long id, Set<Mesto> mesta, String naziv, Boolean obrisano) {
        this.id = id;
        this.mesta = mesta;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public Drzava() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Mesto> getMesta() {
        return mesta;
    }

    public void setMesta(Set<Mesto> mesta) {
        this.mesta = mesta;
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

    public DrzavaDTO toDto() {
        return new DrzavaDTO(this.id, this.naziv, null, this.obrisano);
    }
}
