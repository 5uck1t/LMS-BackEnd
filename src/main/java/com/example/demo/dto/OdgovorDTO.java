package com.example.demo.dto;

import com.example.demo.model.Odgovor;

public class OdgovorDTO {

    private Long id;

    private String odgovor;

    private ZadatakDTO zadatak;

    private Boolean obrisano = false;

    public OdgovorDTO(Long id, String odgovor, ZadatakDTO zadatak, Boolean obrisano) {
        this.id = id;
        this.odgovor = odgovor;
        this.zadatak = zadatak;
        this.obrisano = obrisano;
    }

    public OdgovorDTO() {
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

    public ZadatakDTO getZadatak() {
        return zadatak;
    }

    public void setZadatak(ZadatakDTO zadatak) {
        this.zadatak = zadatak;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Odgovor toEntity(){
        Odgovor e = new Odgovor();

        e.setId(id);
        e.setOdgovor(odgovor);
        e.setObrisano(obrisano);
        e.setZadatak(zadatak.toEntity());

        return e;
    }
}
