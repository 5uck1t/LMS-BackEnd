package com.example.demo.saveDto;

import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.dto.StudijskiProgramDTO;
import com.example.demo.model.GodinaStudija;

import java.util.Set;

public class GodinaStudijaSaveDTO {

    private Long id;
    private String godina;
    private Long studijskiProgram_id;
    private Boolean obrisano = false;

    public GodinaStudijaSaveDTO(Long id, String godina, Long studijskiProgram_id, Boolean obrisano) {
        this.id = id;
        this.godina = godina;
        this.studijskiProgram_id = studijskiProgram_id;
        this.obrisano = obrisano;
    }

    public GodinaStudijaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public Long getStudijskiProgram_id() {
        return studijskiProgram_id;
    }

    public void setStudijskiProgram_id(Long studijskiProgram_id) {
        this.studijskiProgram_id = studijskiProgram_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public GodinaStudija toEntity() {
        GodinaStudija e = new GodinaStudija();
        e.setId(id);
        e.setGodina(godina);
        e.setStudentiNaGodini(null);
        e.setRealizacijePredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }
}
