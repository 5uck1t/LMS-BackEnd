package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.GodinaStudija;
import com.example.demo.saveDto.GodinaStudijaSaveDTO;

public class GodinaStudijaDTO {

    private Long id;
    private String godina;
    private StudijskiProgramDTO studijskiProgram;
    private Set<StudentNaGodiniDTO> studentiNaGodini;
    private Set<RealizacijaPredmetaDTO> realizacijePredmeta;
    private Boolean obrisano = false;

    public GodinaStudijaDTO() {
    }

    public GodinaStudijaDTO(Long id, String godina, StudijskiProgramDTO studijskiProgram, Set<StudentNaGodiniDTO> studentiNaGodini, Set<RealizacijaPredmetaDTO> realizacijePredmeta, Boolean obrisano) {
        this.id = id;
        this.godina = godina;
        this.studijskiProgram = studijskiProgram;
        this.studentiNaGodini = studentiNaGodini;
        this.realizacijePredmeta = realizacijePredmeta;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGodina() { return godina; }
    public void setGodina(String godina) { this.godina = godina; }

    public StudijskiProgramDTO getStudijskiProgram() { return studijskiProgram; }
    public void setStudijskiProgram(StudijskiProgramDTO studijskiProgram) { this.studijskiProgram = studijskiProgram; }

    public Set<StudentNaGodiniDTO> getStudentiNaGodini() { return studentiNaGodini; }
    public void setStudentiNaGodini(Set<StudentNaGodiniDTO> studentiNaGodini) { this.studentiNaGodini = studentiNaGodini; }

    public Set<RealizacijaPredmetaDTO> getRealizacijePredmeta() { return realizacijePredmeta; }
    public void setRealizacijePredmeta(Set<RealizacijaPredmetaDTO> realizacijePredmeta) { this.realizacijePredmeta = realizacijePredmeta; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public GodinaStudija toEntity() {
        GodinaStudija e = new GodinaStudija();
        e.setId(id);
        e.setGodina(godina);
        e.setStudijskiProgram(studijskiProgram.toEntity());
        e.setStudentiNaGodini(null);
        e.setRealizacijePredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }

    public GodinaStudijaSaveDTO toSaveDto() {
        GodinaStudijaSaveDTO e = new GodinaStudijaSaveDTO();
        e.setId(id);
        e.setGodina(godina);
        e.setStudijskiProgram_id(studijskiProgram.getId());
        e.setObrisano(obrisano);
        return e;
    }

}