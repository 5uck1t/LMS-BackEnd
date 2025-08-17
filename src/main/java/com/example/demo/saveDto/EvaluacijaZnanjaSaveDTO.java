package com.example.demo.saveDto;

import com.example.demo.dto.PohadjanjePredmetaDTO;
import com.example.demo.dto.RokDTO;
import com.example.demo.model.EvaluacijaZnanja;

import java.util.Date;

public class EvaluacijaZnanjaSaveDTO {
    private Long id;
    private String naziv;
    private Long realizacijaPredmeta_id;
    private Boolean obrisano = false;

    public EvaluacijaZnanjaSaveDTO(Long id, String naziv, Long realizacijaPredmeta_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
        this.obrisano = obrisano;
    }

    public EvaluacijaZnanjaSaveDTO() {
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

    public Long getRealizacijaPredmeta_id() {
        return realizacijaPredmeta_id;
    }

    public void setRealizacijaPredmeta_id(Long realizacijaPredmeta_id) {
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public EvaluacijaZnanja toEntity() {
        EvaluacijaZnanja e = new EvaluacijaZnanja();
        e.setId(id);
        e.setNaziv(naziv);
        e.setObrisano(obrisano);
        return e;
    }
}
