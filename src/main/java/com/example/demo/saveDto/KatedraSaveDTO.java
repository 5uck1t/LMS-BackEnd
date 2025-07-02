package com.example.demo.saveDto;

import com.example.demo.dto.FakultetDTO;
import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.model.Katedra;

import java.util.Set;

public class KatedraSaveDTO {

    private Long id;
    private String naziv;
    private Long fakultet_id;
    private Long sefKatedre_id;
    private Boolean obrisano = false;

    public KatedraSaveDTO(Long id, String naziv, Long fakultet_id, Long sefKatedre_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.fakultet_id = fakultet_id;
        this.sefKatedre_id = sefKatedre_id;
        this.obrisano = obrisano;
    }

    public KatedraSaveDTO() {
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

    public Long getFakultet_id() {
        return fakultet_id;
    }

    public void setFakultet_id(Long fakultet_id) {
        this.fakultet_id = fakultet_id;
    }

    public Long getSefKatedre_id() {
        return sefKatedre_id;
    }

    public void setSefKatedre_id(Long sefKatedre_id) {
        this.sefKatedre_id = sefKatedre_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Katedra toEntity() {
        Katedra e = new Katedra();
        e.setId(id);
        e.setNaziv(naziv);
        e.setStudijskiProgrami(null);
        e.setObrisano(obrisano);
        return e;
    }
}
