package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Zadatak;
import com.example.demo.saveDto.ZadatakSaveDTO;
import com.example.demo.saveDto.ZvanjeSaveDTO;

public class ZadatakDTO {
    private Long id;
    private String pitanje;
    private EvaluacijaZnanjaDTO evaluacijaZnanja;
    private Boolean obrisano;
    private List<OdgovorDTO> odgovori;  // dodaj listu odgovora

    public ZadatakDTO(Long id, String pitanje, EvaluacijaZnanjaDTO evaluacijaZnanja, Boolean obrisano, List<OdgovorDTO> odgovori) {
        this.id = id;
        this.pitanje = pitanje;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.obrisano = obrisano;
        this.odgovori = odgovori;
    }
    public List<OdgovorDTO> getOdgovori() {
		return odgovori;
	}
	public void setOdgovori(List<OdgovorDTO> odgovori) {
		this.odgovori = odgovori;
	}
	public ZadatakDTO() {
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

    public Zadatak toEntity(){
        return new Zadatak(this.id, this.pitanje, this.getEvaluacijaZnanja().toEntity(), null, this.obrisano);
    }

    public ZadatakSaveDTO toSaveDto() {
        ZadatakSaveDTO e = new ZadatakSaveDTO();
        e.setId(id);
        e.setPitanje(pitanje);
        e.setEvaluacijaZnanja_id(evaluacijaZnanja.getId());
        e.setObrisano(obrisano);
        return e;
    }
}
