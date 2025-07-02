package com.example.demo.saveDto;

import com.example.demo.dto.PredmetDTO;
import com.example.demo.model.Silabus;

public class SilabusSaveDTO {

    private Long id;
    private String opis;
    private Long predmet_id;
    private Boolean obrisano = false;

    public SilabusSaveDTO(Long id, String opis, Long predmet_id, Boolean obrisano) {
        this.id = id;
        this.opis = opis;
        this.predmet_id = predmet_id;
        this.obrisano = obrisano;
    }

    public SilabusSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getPredmet_id() {
        return predmet_id;
    }

    public void setPredmet_id(Long predmet_id) {
        this.predmet_id = predmet_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Silabus toEntity() {
        Silabus e = new Silabus();
        e.setId(id);
        e.setOpis(opis);
        e.setObrisano(obrisano);
        return e;
    }
}
