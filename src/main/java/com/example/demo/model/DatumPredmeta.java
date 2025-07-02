package com.example.demo.model;


import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.EvaluacijaZnanjaDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
public class DatumPredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Rok rok;

    @ManyToOne
    private RealizacijaPredmeta realizacijaPredmeta;

    private Date datum;

    @ColumnDefault("false")
    private Boolean obrisano;

    public DatumPredmeta(Date datum, Long id, Boolean obrisano, RealizacijaPredmeta realizacijaPredmeta, Rok rok) {
        this.datum = datum;
        this.id = id;
        this.obrisano = obrisano;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.rok = rok;
    }

    public DatumPredmeta() {
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RealizacijaPredmeta getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmeta realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public Rok getRok() {
        return rok;
    }

    public void setRok(Rok rok) {
        this.rok = rok;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public DatumPredmetaDTO toDto() {
        return new DatumPredmetaDTO(this.datum, this.id, this.obrisano, this.realizacijaPredmeta.toDto(), this.rok.toDto());
    }
}
