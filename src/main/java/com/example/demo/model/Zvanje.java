package com.example.demo.model;

import com.example.demo.dto.ZvanjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class Zvanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "zvanje")
    private Set<NastavnikHasZvanje> nastavnikHasZvanje;

    private Date datumIzbora;

    private Date datumPrestanka;

    @ManyToOne
    private TipZvanja tipZvanja;

    @ManyToOne
    private NaucnaOblast naucnaOblast;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Zvanje(Date datumIzbora, Date datumPrestanka, Long id, Set<NastavnikHasZvanje> nastavnikHasZvanje, NaucnaOblast naucnaOblast, Boolean obrisano, TipZvanja tipZvanja) {
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.id = id;
        this.nastavnikHasZvanje = nastavnikHasZvanje;
        this.naucnaOblast = naucnaOblast;
        this.obrisano = obrisano;
        this.tipZvanja = tipZvanja;
    }

    public Zvanje() {
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<NastavnikHasZvanje> getNastavnikHasZvanje() {
        return nastavnikHasZvanje;
    }

    public void setNastavnikHasZvanje(Set<NastavnikHasZvanje> nastavnikHasZvanje) {
        this.nastavnikHasZvanje = nastavnikHasZvanje;
    }

    public NaucnaOblast getNaucnaOblast() {
        return naucnaOblast;
    }

    public void setNaucnaOblast(NaucnaOblast naucnaOblast) {
        this.naucnaOblast = naucnaOblast;
    }

    public TipZvanja getTipZvanja() {
        return tipZvanja;
    }

    public void setTipZvanja(TipZvanja tipZvanja) {
        this.tipZvanja = tipZvanja;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public ZvanjeDTO toDto() {
        return new ZvanjeDTO(this.id, null, this.datumIzbora, this.datumPrestanka, this.tipZvanja.toDto(), this.naucnaOblast.toDto(), this.obrisano);
    }
}
