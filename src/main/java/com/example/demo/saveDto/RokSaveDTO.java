package com.example.demo.saveDto;

import com.example.demo.dto.DatumPredmetaDTO;
import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.model.Rok;

import java.util.Date;
import java.util.Set;

public class RokSaveDTO {

    private Long id;
    private String naziv;
    private Date pocetak;
    private Date kraj;
    private Boolean obrisano = false;

    public RokSaveDTO(Long id, String naziv, Date pocetak, Date kraj, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.obrisano = obrisano;
    }

    public RokSaveDTO() {
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

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Rok toEntity() {
        Rok e = new Rok();
        e.setId(id);
        e.setNaziv(naziv);
        e.setPocetak(pocetak);
        e.setKraj(kraj);
        e.setObrisano(obrisano);
        e.setDatumiPredmeta(null);
        return e;
    }
}
