package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Silabus;
import com.example.demo.saveDto.SilabusSaveDTO;

public class SilabusDTO {

    private Long id;
    private String opis;
    private PredmetDTO predmet;
    private Boolean obrisano = false;

    public SilabusDTO() {
    }

    public SilabusDTO(Long id, String opis, PredmetDTO predmet, Boolean obrisano) {
        this.id = id;
        this.opis = opis;
        this.predmet = predmet;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public PredmetDTO getPredmet() { return predmet; }
    public void setPredmet(PredmetDTO predmet) { this.predmet = predmet; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Silabus toEntity() {
        Silabus e = new Silabus();
        e.setId(id);
        e.setOpis(opis);
        e.setPredmet(predmet.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public SilabusSaveDTO toSaveDto() {
        SilabusSaveDTO e = new SilabusSaveDTO();
        e.setId(id);
        e.setOpis(opis);
        e.setPredmet_id(predmet.getId());
        e.setObrisano(obrisano);
        return e;
    }

}