package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Katedra;
import com.example.demo.saveDto.KatedraSaveDTO;

public class KatedraDTO {

    private Long id;
    private String naziv;
    private FakultetDTO fakultet;
    private NastavnikDTO sefKatedre;
    private Set<StudijskiProgramDTO> studijskiProgrami;
    private Boolean obrisano = false;

    public KatedraDTO() {
    }

    public KatedraDTO(Long id, String naziv, FakultetDTO fakultet, NastavnikDTO sefKatedre, Set<StudijskiProgramDTO> studijskiProgrami, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.fakultet = fakultet;
        this.sefKatedre = sefKatedre;
        this.studijskiProgrami = studijskiProgrami;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public FakultetDTO getFakultet() { return fakultet; }
    public void setFakultet(FakultetDTO fakultet) { this.fakultet = fakultet; }

    public NastavnikDTO getSefKatedre() { return sefKatedre; }
    public void setSefKatedre(NastavnikDTO sefKatedre) { this.sefKatedre = sefKatedre; }

    public Set<StudijskiProgramDTO> getStudijskiProgrami() { return studijskiProgrami; }
    public void setStudijskiProgrami(Set<StudijskiProgramDTO> studijskiProgrami) { this.studijskiProgrami = studijskiProgrami; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Katedra toEntity() {
        Katedra e = new Katedra();
        e.setId(id);
        e.setNaziv(naziv);
        e.setFakultet(fakultet.toEntity());
        e.setSefKatedre(sefKatedre.toEntity());
        e.setStudijskiProgrami(null);
        e.setObrisano(obrisano);
        return e;
    }

    public KatedraSaveDTO toSaveDto() {
        KatedraSaveDTO e = new KatedraSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setFakultet_id(fakultet.getId());
        e.setSefKatedre_id(sefKatedre.getId());
        e.setObrisano(obrisano);
        return e;
    }

}