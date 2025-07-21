package com.example.demo.saveDto;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.model.PohadjanjePredmeta;

import java.util.Set;

public class PohadjanjePredmetaSaveDTO {

    private Long id;
    private int konacnaOcena;
    private Long studentNaGodini_id;
    private Long realizacijaPredmeta_id;
    private Boolean obrisano = false;

    public PohadjanjePredmetaSaveDTO(Long id, int konacnaOcena, Long studentNaGodini_id, Long realizacijaPredmeta_id, Boolean obrisano) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.studentNaGodini_id = studentNaGodini_id;
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
        this.obrisano = obrisano;
    }

    public PohadjanjePredmetaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKonacnaOcena() {
        return konacnaOcena;
    }

    public void setKonacnaOcena(int konacnaOcena) {
        this.konacnaOcena = konacnaOcena;
    }

    public Long getStudentNaGodini_id() {
        return studentNaGodini_id;
    }

    public void setStudentNaGodini_id(Long studentNaGodini_id) {
        this.studentNaGodini_id = studentNaGodini_id;
    }

    public Long getRealizacijaPredmeta_id() {
        return realizacijaPredmeta_id;
    }

    public void setRealizacijaPredmeta_id(Long realizacijaPredmeta_id) {
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PohadjanjePredmeta toEntity() {
        PohadjanjePredmeta e = new PohadjanjePredmeta();
        e.setId(id);
        e.setKonacnaOcena(konacnaOcena);
        e.setObrisano(obrisano);
        return e;
    }
}
