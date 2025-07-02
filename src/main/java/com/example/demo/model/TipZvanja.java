package com.example.demo.model;

import com.example.demo.dto.TipZvanjaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class TipZvanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    @OneToMany(mappedBy = "tipZvanja")

    private Set<Zvanje> zvanje;

    @ColumnDefault("false")
    private Boolean obrisano;

    public TipZvanja(Long id, String naziv, Boolean obrisano, Set<Zvanje> zvanje) {
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
        this.zvanje = zvanje;
    }

    public TipZvanja() {
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

    public Set<Zvanje> getZvanje() {
        return zvanje;
    }

    public void setZvanje(Set<Zvanje> zvanje) {
        this.zvanje = zvanje;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public TipZvanjaDTO toDto() {
        return new TipZvanjaDTO(this.id, this.naziv, null, this.obrisano);
    }
}
