package com.example.demo.saveDto;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.dto.FakultetDTO;
import com.example.demo.model.Univerzitet;

import java.util.Date;
import java.util.Set;

public class UniverzitetSaveDTO {

    private Long id;
    private Date datumOsnivanja;
    private Long adresa_id;
    private Boolean obrisano = false;

    public UniverzitetSaveDTO(Long id, Date datumOsnivanja, Long adresa_id, Boolean obrisano) {
        this.id = id;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa_id = adresa_id;
        this.obrisano = obrisano;
    }

    public UniverzitetSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumOsnivanja() {
        return datumOsnivanja;
    }

    public void setDatumOsnivanja(Date datumOsnivanja) {
        this.datumOsnivanja = datumOsnivanja;
    }

    public Long getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Long adresa_id) {
        this.adresa_id = adresa_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Univerzitet toEntity() {
        Univerzitet e = new Univerzitet();
        e.setId(id);
        e.setDatumOsnivanja(datumOsnivanja);
        e.setFakulteti(null);
        e.setObrisano(obrisano);
        return e;
    }


}
