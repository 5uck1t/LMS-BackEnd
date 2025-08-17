package com.example.demo.saveDto;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.dto.RokDTO;
import com.example.demo.model.Polaganje;

import java.util.Date;

public class PolaganjeSaveDTO {

    private Long id;

    private Date datum;

    private Long rok_id;

    private Long evaluacijaZnanja_id;

    private Boolean obrisano = false;

    public PolaganjeSaveDTO(Long id, Date datum, Long rok_id, Long evaluacijaZnanja_id, Boolean obrisano) {
        this.id = id;
        this.datum = datum;
        this.rok_id = rok_id;
        this.evaluacijaZnanja_id = evaluacijaZnanja_id;
        this.obrisano = obrisano;
    }

    public PolaganjeSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Long getRok_id() {
        return rok_id;
    }

    public void setRok_id(Long rok_id) {
        this.rok_id = rok_id;
    }

    public Long getEvaluacijaZnanja_id() {
        return evaluacijaZnanja_id;
    }

    public void setEvaluacijaZnanja_id(Long evaluacijaZnanja_id) {
        this.evaluacijaZnanja_id = evaluacijaZnanja_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Polaganje toEntity(){
        Polaganje e = new Polaganje();

        e.setId(id);
        e.setDatum(datum);
        e.setObrisano(obrisano);

        return e;
    }
}
