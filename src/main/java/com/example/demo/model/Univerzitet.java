package com.example.demo.model;

import com.example.demo.dto.UniverzitetDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class Univerzitet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private Date datumOsnivanja;

    @ManyToOne
    private Adresa adresa;

    @OneToMany(mappedBy = "univerzitet")
    private Set<Fakultet> fakulteti;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Univerzitet(Adresa adresa, Date datumOsnivanja, Set<Fakultet> fakulteti, Long id, String naziv, Boolean obrisano) {
        this.adresa = adresa;
        this.datumOsnivanja = datumOsnivanja;
        this.fakulteti = fakulteti;
        this.id = id;
        this.naziv = naziv;
        this.obrisano = obrisano;
    }

    public Univerzitet() {
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public Set<Fakultet> getFakulteti() {
        return fakulteti;
    }

    public void setFakulteti(Set<Fakultet> fakulteti) {
        this.fakulteti = fakulteti;
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

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public UniverzitetDTO toDto() {
        return new UniverzitetDTO(this.id, this.datumOsnivanja, this.adresa.toDto(), null, this.obrisano);
    }
}
