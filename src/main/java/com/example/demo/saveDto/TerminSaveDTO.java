package com.example.demo.saveDto;

import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.model.Termin;

import java.time.LocalDate;
import java.time.LocalTime;

public class TerminSaveDTO {

    private Long id;

    private LocalDate datum;

    private LocalTime vremePocetka;

    private LocalTime vremeKraja;

    private Long realizacijaPredmeta_id;

    private Boolean obrisano;
    
    private String ishod;

    public TerminSaveDTO(Long id, LocalDate datum, LocalTime vremePocetka, LocalTime vremeKraja, Long realizacijaPredmeta_id, Boolean obrisano, String ishod) {
        this.id = id;
        this.datum = datum;
        this.vremePocetka = vremePocetka;
        this.vremeKraja = vremeKraja;
        this.realizacijaPredmeta_id = realizacijaPredmeta_id;
        this.obrisano = obrisano;
        this.ishod = ishod;
    }

    public String getIshod() {
		return ishod;
	}

	public void setIshod(String ishod) {
		this.ishod = ishod;
	}

	public TerminSaveDTO() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public LocalTime getVremeKraja() {
        return vremeKraja;
    }

    public void setVremeKraja(LocalTime vremeKraja) {
        this.vremeKraja = vremeKraja;
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

    public Termin toEntity() {
        Termin e = new Termin();
        e.setId(id);
        e.setDatum(datum);
        e.setVremePocetka(vremePocetka);
        e.setVremeKraja(vremeKraja);
        e.setObrisano(obrisano);
        e.setIshod(ishod);
        return e;
    }
}
