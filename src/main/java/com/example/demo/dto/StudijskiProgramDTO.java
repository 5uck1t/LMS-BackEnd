package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.StudijskiProgram;
import com.example.demo.saveDto.StudijskiProgramSaveDTO;

public class StudijskiProgramDTO {

    private Long id;
    private String naziv;
    private KatedraDTO katedra;
    private Set<GodinaStudijaDTO> godineStudija;
    private Boolean obrisano = false;

    public StudijskiProgramDTO() {
    }

    public StudijskiProgramDTO(Long id, String naziv, KatedraDTO katedra, Set<GodinaStudijaDTO> godineStudija, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.katedra = katedra;
        this.godineStudija = godineStudija;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public KatedraDTO getKatedra() { return katedra; }
    public void setKatedra(KatedraDTO katedra) { this.katedra = katedra; }

    public Set<GodinaStudijaDTO> getGodineStudija() { return godineStudija; }
    public void setGodineStudija(Set<GodinaStudijaDTO> godineStudija) { this.godineStudija = godineStudija; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public StudijskiProgram toEntity() {
        StudijskiProgram e = new StudijskiProgram();
        e.setId(id);
        e.setNaziv(naziv);
        e.setKatedra(katedra.toEntity());
        e.setGodineStudija(null);
        e.setObrisano(obrisano);
        return e;
    }

    public StudijskiProgramSaveDTO toSaveDto() {
        StudijskiProgramSaveDTO e = new StudijskiProgramSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setKatedra_id(katedra.getId());
        e.setObrisano(obrisano);
        return e;
    }

}