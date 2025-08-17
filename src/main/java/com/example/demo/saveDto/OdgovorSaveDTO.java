package com.example.demo.saveDto;


import com.example.demo.dto.OdgovorDTO;
import com.example.demo.model.Odgovor;

public class OdgovorSaveDTO {

    private Long id;

    private String odgovor;

    private Long zadatak_id;

    private Boolean tacan;

    private Boolean obrisano = false;

    public OdgovorSaveDTO(Long id, String odgovor, Long zadatak_id, Boolean obrisano,Boolean tacan) {
        this.id = id;
        this.odgovor = odgovor;
        this.zadatak_id = zadatak_id;
        this.obrisano = obrisano;
        this.tacan = tacan;
    }

    public OdgovorSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public Long getZadatak_id() {
        return zadatak_id;
    }

    public void setZadatak_id(Long zadatak_id) {
        this.zadatak_id = zadatak_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Boolean getTacan() {
        return tacan;
    }

    public void setTacan(Boolean tacan) {
        this.tacan = tacan;
    }

    public Odgovor toEntity(){
        Odgovor e = new Odgovor();

        e.setId(id);
        e.setOdgovor(odgovor);
        e.setObrisano(obrisano);
        e.setTacan(tacan);
        return e;
    }
}
