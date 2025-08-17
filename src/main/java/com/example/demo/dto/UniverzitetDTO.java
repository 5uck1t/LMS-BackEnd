package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Univerzitet;
import com.example.demo.saveDto.UniverzitetSaveDTO;

public class UniverzitetDTO {

    private Long id;
    private Date datumOsnivanja;
    private AdresaDTO adresa;
    private Set<FakultetDTO> fakulteti;
    private Boolean obrisano = false;

    public UniverzitetDTO() {
    }

    public UniverzitetDTO(Long id, Date datumOsnivanja, AdresaDTO adresa, Set<FakultetDTO> fakulteti, Boolean obrisano) {
        this.id = id;
        this.datumOsnivanja = datumOsnivanja;
        this.adresa = adresa;
        this.fakulteti = fakulteti;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDatumOsnivanja() { return datumOsnivanja; }
    public void setDatumOsnivanja(Date datumOsnivanja) { this.datumOsnivanja = datumOsnivanja; }

    public AdresaDTO getAdresa() { return adresa; }
    public void setAdresa(AdresaDTO adresa) { this.adresa = adresa; }

    public Set<FakultetDTO> getFakulteti() { return fakulteti; }
    public void setFakulteti(Set<FakultetDTO> fakulteti) { this.fakulteti = fakulteti; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Univerzitet toEntity() {
        Univerzitet e = new Univerzitet();
        e.setId(id);
        e.setDatumOsnivanja(datumOsnivanja);
        e.setAdresa(adresa.toEntity());
        e.setFakulteti(null);
        e.setObrisano(obrisano);
        return e;
    }

    public UniverzitetSaveDTO toSaveDto() {
        UniverzitetSaveDTO e = new UniverzitetSaveDTO();
        e.setId(id);
        e.setDatumOsnivanja(datumOsnivanja);
        e.setObrisano(obrisano);
        return e;
    }

}