package com.example.demo.saveDto;

import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.dto.RokDTO;
import com.example.demo.model.DatumPredmeta;

import java.util.Date;

public class DatumPredmetaSaveDTO {

    private Long id;
    private Long rok_id;
    private Long realizacijaPredmeta_id;
    private Date datum;
    private boolean obrisano = false;

    public DatumPredmetaSaveDTO(Long id, Long rok_id, Long realizacijaPredmeta_id, Date datum, boolean obrisano) {
        this.id = id;
        this.rok_id = rok_id;
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
        this.datum = datum;
        this.obrisano = obrisano;
    }

    public DatumPredmetaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRok_id() {
        return rok_id;
    }

    public void setRok_id(Long rok_id) {
        this.rok_id = rok_id;
    }

    public Long getRealizacijaPredmeta_id() {
        return realizacijaPredmeta_id;
    }

    public void setRealizacijaPredmeta_id(Long realizacijaPredmeta_id) {
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isObrisano() {
        return obrisano;
    }

    public void setObrisano(boolean obrisano) {
        this.obrisano = obrisano;
    }

    public DatumPredmeta toEntity() {
        DatumPredmeta e = new DatumPredmeta();
        e.setId(id);
        e.setDatum(datum);
        e.setObrisano(obrisano);
        return e;
    }
}
