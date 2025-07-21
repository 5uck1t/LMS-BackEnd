package com.example.demo.saveDto;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.model.Zadatak;

public class ZadatakSaveDTO {

    private Long id;

    private String pitanje;

    private Long evaluacijaZnanja_id;

    private Boolean obrisano = false;

    public ZadatakSaveDTO(Long id, String pitanje, Long evaluacijaZnanja_id, Boolean obrisano) {
        this.id = id;
        this.pitanje = pitanje;
        this.evaluacijaZnanja_id = evaluacijaZnanja_id;
        this.obrisano = obrisano;
    }

    public ZadatakSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
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

    public Zadatak toEntity(){
        Zadatak e = new Zadatak();

        e.setId(id);
        e.setPitanje(pitanje);

        return e;
    }
}
