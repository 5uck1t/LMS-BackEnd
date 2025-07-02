package com.example.demo.model;

import java.util.Set;

import com.example.demo.dto.PravoPristupaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class PravoPristupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @OneToMany(mappedBy = "pravoPristupa")
    private Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa;

    @ColumnDefault("false")
    private Boolean obrisano;

    public PravoPristupa(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa, Long id, String naziv, Boolean obrisano) {
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public PravoPristupa() {
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

    public Set<DodeljenoPravoPristupa> getDodeljenaPravaPristupa() {
        return dodeljenaPravaPristupa;
    }

    public void setDodeljenaPravaPristupa(Set<DodeljenoPravoPristupa> dodeljenaPravaPristupa) {
        this.dodeljenaPravaPristupa = dodeljenaPravaPristupa;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PravoPristupaDTO toDto() {
        return new PravoPristupaDTO(this.id, this.naziv, null, this.obrisano);
    }
}

