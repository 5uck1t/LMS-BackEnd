package com.example.demo.saveDto;

import com.example.demo.dto.*;
import com.example.demo.model.RealizacijaPredmeta;

import java.util.Set;

public class RealizacijaPredmetaSaveDTO {

    private Long id;
    private Long godinaStudija_id;
    private Long predmet_id;
    private Long nastavnik_id;
    private Boolean obrisano = false;

    public RealizacijaPredmetaSaveDTO(Long id, Long godinaStudija_id, Long predmet_id, Long nastavnik_id, Boolean obrisano) {
        this.id = id;
        this.godinaStudija_id = godinaStudija_id;
        this.predmet_id = predmet_id;
        this.nastavnik_id = nastavnik_id;
        this.obrisano = obrisano;
    }

    public RealizacijaPredmetaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGodinaStudija_id() {
        return godinaStudija_id;
    }

    public void setGodinaStudija_id(Long godinaStudija_id) {
        this.godinaStudija_id = godinaStudija_id;
    }

    public Long getPredmet_id() {
        return predmet_id;
    }

    public void setPredmet_id(Long predmet_id) {
        this.predmet_id = predmet_id;
    }

    public Long getNastavnik_id() {
        return nastavnik_id;
    }

    public void setNastavnik_id(Long nastavnik_id) {
        this.nastavnik_id = nastavnik_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public RealizacijaPredmeta toEntity() {
        RealizacijaPredmeta e = new RealizacijaPredmeta();
        e.setId(id);
        e.setPohadjanjaPredmeta(null);
        e.setObrisano(obrisano);
        e.setDatumiPredmeta(null);
        return e;
    }
}
