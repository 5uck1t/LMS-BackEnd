package com.example.demo.model;

import com.example.demo.dto.DodeljenoPravoPristupaDTO;
import com.example.demo.dto.DrzavaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class DodeljenoPravoPristupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UlogovaniKorisnik ulogovaniKorisnik;
    @ManyToOne
    private PravoPristupa pravoPristupa;

    @ColumnDefault("false")
    private Boolean obrisano;

    public DodeljenoPravoPristupa(Long id, Boolean obrisano, PravoPristupa pravoPristupa, UlogovaniKorisnik ulogovaniKorisnik) {
        this.id = id;
        this.obrisano = obrisano;
        this.pravoPristupa = pravoPristupa;
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

    public DodeljenoPravoPristupa() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PravoPristupa getPravoPristupa() {
        return pravoPristupa;
    }

    public void setPravoPristupa(PravoPristupa pravoPristupa) {
        this.pravoPristupa = pravoPristupa;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public UlogovaniKorisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }

    public void setUlogovaniKorisnik(UlogovaniKorisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

    public DodeljenoPravoPristupaDTO toDto() {
        return new DodeljenoPravoPristupaDTO(this.id, this.ulogovaniKorisnik.toDto(), this.pravoPristupa.toDto(), this.obrisano);
    }
}

