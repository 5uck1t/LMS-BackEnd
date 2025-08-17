package com.example.demo.dto;

import com.example.demo.model.DatumPredmeta;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.saveDto.DatumPredmetaSaveDTO;

import java.util.Date;

public class DatumPredmetaDTO {

    private Long id;

    private RokDTO rok;

    private RealizacijaPredmetaDTO realizacijaPredmeta;

    private Date datum;

    private boolean obrisano = false;

    public DatumPredmetaDTO(Date datum, Long id, boolean obrisano, RealizacijaPredmetaDTO realizacijaPredmeta, RokDTO rok) {
        this.datum = datum;
        this.id = id;
        this.obrisano = obrisano;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.rok = rok;
    }

    public DatumPredmetaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
    }

    public RokDTO getRok() {
        return rok;
    }

    public void setRok(RokDTO rok) {
        this.rok = rok;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public DatumPredmeta toEntity() {
        DatumPredmeta e = new DatumPredmeta();
        e.setId(id);
        e.setRok(rok.toEntity());
        e.setRealizacijaPredmeta(realizacijaPredmeta.toEntity());
        e.setDatum(datum);
        e.setObrisano(obrisano);
        return e;
    }

    public DatumPredmetaSaveDTO toSaveDto() {
        DatumPredmetaSaveDTO e = new DatumPredmetaSaveDTO();
        e.setId(id);
        e.setRok_id(rok.getId());
        e.setRealizacijaPredmeta_id(realizacijaPredmeta.getId());
        e.setDatum(datum);
        e.setObrisano(obrisano);
        return e;
    }
}
