package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.EvaluacijaZnanja;
import com.example.demo.saveDto.EvaluacijaZnanjaSaveDTO;

public class EvaluacijaZnanjaDTO {

    private Long id;
    private String naziv;
    private RealizacijaPredmetaDTO realizacijaPredmeta;
    private Boolean obrisano = false;

    public EvaluacijaZnanjaDTO() {
    }

    public EvaluacijaZnanjaDTO(Long id, String naziv, RealizacijaPredmetaDTO realizacijaPredmeta, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.obrisano = obrisano;
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

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() {
        return realizacijaPredmeta;
    }

    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) {
        this.realizacijaPredmeta = realizacijaPredmeta;
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
        e.setRealizacijaPredmeta(realizacijaPredmeta.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public EvaluacijaZnanjaSaveDTO toSaveDto() {
        EvaluacijaZnanjaSaveDTO e = new EvaluacijaZnanjaSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setRealizacijaPredmeta_id(realizacijaPredmeta.getId());
        e.setObrisano(obrisano);
        return e;
    }

}