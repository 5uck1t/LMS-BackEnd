package com.example.demo.saveDto;

import com.example.demo.dto.GodinaStudijaDTO;
import com.example.demo.dto.KatedraDTO;
import com.example.demo.model.StudijskiProgram;

import java.util.Set;

public class StudijskiProgramSaveDTO {

    private Long id;
    private String naziv;
    private Long katedra_id;
    private Boolean obrisano = false;

    public StudijskiProgramSaveDTO(Long id, String naziv, Long katedra_id, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.katedra_id = katedra_id;
        this.obrisano = obrisano;
    }

    public StudijskiProgramSaveDTO() {
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

    public Long getKatedra_id() {
        return katedra_id;
    }

    public void setKatedra_id(Long katedra_id) {
        this.katedra_id = katedra_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public StudijskiProgram toEntity() {
        StudijskiProgram e = new StudijskiProgram();
        e.setId(id);
        e.setNaziv(naziv);
        e.setGodineStudija(null);
        e.setObrisano(obrisano);
        return e;
    }
}
