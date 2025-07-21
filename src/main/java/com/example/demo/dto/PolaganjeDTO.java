package com.example.demo.dto;


import com.example.demo.model.Polaganje;
import com.example.demo.saveDto.PolaganjeSaveDTO;

import java.util.Date;

public class PolaganjeDTO {

    private Long id;

    private Date datum;

    private RokDTO rok;

    private EvaluacijaZnanjaDTO evaluacijaZnanja;

    private Boolean obrisano = false;

    public PolaganjeDTO(Long id, Date datum, RokDTO rok, EvaluacijaZnanjaDTO evaluacijaZnanja, Boolean obrisano) {
        this.id = id;
        this.datum = datum;
        this.rok = rok;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.obrisano = obrisano;
    }

    public PolaganjeDTO() {
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

    public RokDTO getRok() {
        return rok;
    }

    public void setRok(RokDTO rok) {
        this.rok = rok;
    }

    public EvaluacijaZnanjaDTO getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(EvaluacijaZnanjaDTO evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
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
        e.setRok(rok.toEntity());
        e.setEvaluacijaZnanja(evaluacijaZnanja.toEntity());

        return e;
    }

    public PolaganjeSaveDTO toSaveDto(){
        PolaganjeSaveDTO e = new PolaganjeSaveDTO();

        e.setId(id);
        e.setDatum(datum);
        e.setRok_id(rok.getId());
        e.setEvaluacijaZnanja_id(evaluacijaZnanja.getId());
        e.setObrisano(obrisano);

        return e;
    }
}
