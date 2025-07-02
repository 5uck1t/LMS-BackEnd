package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Rok;
import com.example.demo.saveDto.RokSaveDTO;

public class RokDTO {

    private Long id;
    private String naziv;
    private Date pocetak;
    private Date kraj;
    private Set<EvaluacijaZnanjaDTO> evaluacijeZnanja;
    private Set<DatumPredmetaDTO> datumiPredmeta;
    private Boolean obrisano = false;

    public RokDTO() {
    }

    public RokDTO(Long id, String naziv, Date pocetak, Date kraj, Set<EvaluacijaZnanjaDTO> evaluacijeZnanja, Boolean obrisano, Set<DatumPredmetaDTO> datumiPredmeta) {
        this.id = id;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.evaluacijeZnanja = evaluacijeZnanja;
        this.obrisano = obrisano;
        this.datumiPredmeta = datumiPredmeta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Date getPocetak() { return pocetak; }
    public void setPocetak(Date pocetak) { this.pocetak = pocetak; }

    public Date getKraj() { return kraj; }
    public void setKraj(Date kraj) { this.kraj = kraj; }

    public Set<EvaluacijaZnanjaDTO> getEvaluacijeZnanja() { return evaluacijeZnanja; }
    public void setEvaluacijeZnanja(Set<EvaluacijaZnanjaDTO> evaluacijeZnanja) { this.evaluacijeZnanja = evaluacijeZnanja; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Set<DatumPredmetaDTO> getDatumiPredmeta() {
        return datumiPredmeta;
    }

    public void setDatumiPredmeta(Set<DatumPredmetaDTO> datumiPredmeta) {
        this.datumiPredmeta = datumiPredmeta;
    }

    public Rok toEntity() {
        Rok e = new Rok();
        e.setId(id);
        e.setNaziv(naziv);
        e.setPocetak(pocetak);
        e.setKraj(kraj);
        e.setEvaluacijeZnanja(null);
        e.setObrisano(obrisano);
        e.setDatumiPredmeta(null);
        return e;
    }

    public RokSaveDTO toSaveDto() {
        RokSaveDTO e = new RokSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setPocetak(pocetak);
        e.setKraj(kraj);
        e.setObrisano(obrisano);
        return e;
    }
}