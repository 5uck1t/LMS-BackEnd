package com.example.demo.model;

import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.dto.RokDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class Rok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private Date pocetak;

    private Date kraj;

    @OneToMany(mappedBy = "rok")
    private Set<Polaganje> polaganja;

    @OneToMany(mappedBy = "rok")
    private Set<DatumPredmeta> datumiPredmeta;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Rok(Set<Polaganje> polaganja, Long id, Date kraj, String naziv, Boolean obrisano, Date pocetak, Set<DatumPredmeta> datumiPredmeta) {
        this.polaganja = polaganja;
        this.id = id;
        this.kraj = kraj;
        this.naziv = naziv;
        this.obrisano = obrisano;
        this.pocetak = pocetak;
        this.datumiPredmeta = datumiPredmeta;
    }

    public Rok() {
    }

    public Set<Polaganje> getPolaganja() {
        return polaganja;
    }

    public void setPolaganja(Set<Polaganje> polaganja) {
        this.polaganja = polaganja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Set<DatumPredmeta> getDatumiPredmeta() {
        return datumiPredmeta;
    }

    public void setDatumiPredmeta(Set<DatumPredmeta> datumiPredmeta) {
        this.datumiPredmeta = datumiPredmeta;
    }

    public RokDTO toDto() {
        return new RokDTO(this.id, this.naziv, this.pocetak, this.kraj, null, this.obrisano, null);
    }
}
