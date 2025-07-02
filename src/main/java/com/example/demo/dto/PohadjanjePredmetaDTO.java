package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.saveDto.PohadjanjePredmetaSaveDTO;

public class PohadjanjePredmetaDTO {

    private Long id;
    private int konacnaOcena;
    private StudentNaGodiniDTO studentNaGodini;
    private RealizacijaPredmetaDTO realizacijaPredmeta;
    private Set<EvaluacijaZnanjaDTO> evaluacijeZnanja;
    private Boolean obrisano = false;

    public PohadjanjePredmetaDTO() {
    }

    public PohadjanjePredmetaDTO(Long id, int konacnaOcena, StudentNaGodiniDTO studentNaGodini, RealizacijaPredmetaDTO realizacijaPredmeta, Set<EvaluacijaZnanjaDTO> evaluacijeZnanja, Boolean obrisano) {
        this.id = id;
        this.konacnaOcena = konacnaOcena;
        this.studentNaGodini = studentNaGodini;
        this.realizacijaPredmeta = realizacijaPredmeta;
        this.evaluacijeZnanja = evaluacijeZnanja;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getKonacnaOcena() { return konacnaOcena; }
    public void setKonacnaOcena(int konacnaOcena) { this.konacnaOcena = konacnaOcena; }

    public StudentNaGodiniDTO getStudentNaGodini() { return studentNaGodini; }
    public void setStudentNaGodini(StudentNaGodiniDTO studentNaGodini) { this.studentNaGodini = studentNaGodini; }

    public RealizacijaPredmetaDTO getRealizacijaPredmeta() { return realizacijaPredmeta; }
    public void setRealizacijaPredmeta(RealizacijaPredmetaDTO realizacijaPredmeta) { this.realizacijaPredmeta = realizacijaPredmeta; }

    public Set<EvaluacijaZnanjaDTO> getEvaluacijeZnanja() { return evaluacijeZnanja; }
    public void setEvaluacijeZnanja(Set<EvaluacijaZnanjaDTO> evaluacijeZnanja) { this.evaluacijeZnanja = evaluacijeZnanja; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public PohadjanjePredmeta toEntity() {
        PohadjanjePredmeta e = new PohadjanjePredmeta();
        e.setId(id);
        e.setKonacnaOcena(konacnaOcena);
        e.setStudentNaGodini(studentNaGodini.toEntity());
        e.setRealizacijaPredmeta(realizacijaPredmeta.toEntity());
        e.setEvaluacijeZnanja(null);
        e.setObrisano(obrisano);
        return e;
    }

    public PohadjanjePredmetaSaveDTO toSaveDto() {
        PohadjanjePredmetaSaveDTO e = new PohadjanjePredmetaSaveDTO();
        e.setId(id);
        e.setKonacnaOcena(konacnaOcena);
        e.setStudentNaGodini_id(studentNaGodini.getId());
        e.setRealizacijaPredmeta_id(realizacijaPredmeta.getId());
        e.setObrisano(obrisano);
        return e;
    }

}