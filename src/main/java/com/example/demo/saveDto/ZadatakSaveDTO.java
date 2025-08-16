package com.example.demo.saveDto;

import com.example.demo.model.Zadatak;
import com.example.demo.model.Odgovor;

import java.util.ArrayList;
import java.util.List;

public class ZadatakSaveDTO {

    private Long id;
    private String pitanje;
    private List<String> odgovori = new ArrayList<>();
    private Long evaluacijaZnanja_id;
    private Boolean obrisano = false;

    public Boolean getObrisano() {
		return obrisano;
	}

	public void setObrisano(Boolean obrisano) {
		this.obrisano = obrisano;
	}

	public ZadatakSaveDTO() {}

    public ZadatakSaveDTO(Long id, String pitanje, List<String> odgovori, Long evaluacijaZnanja_id) {
        this.id = id;
        this.pitanje = pitanje;
        this.odgovori = odgovori;
        this.evaluacijaZnanja_id = evaluacijaZnanja_id;
    }

    // Kreira entitet Zadatak (bez odgovora)
    public Zadatak toEntity() {
        Zadatak zadatak = new Zadatak();
        zadatak.setId(this.id);
        zadatak.setPitanje(this.pitanje);
        return zadatak;
    }

    // Kreira listu entiteta Odgovor povezanih sa zadatkom
    public List<Odgovor> toOdgovorEntities(Zadatak zadatak) {
        List<Odgovor> lista = new ArrayList<>();
        if (this.odgovori != null) {
            for (String tekst : this.odgovori) {
                Odgovor odg = new Odgovor();
                odg.setOdgovor(tekst);
                odg.setZadatak(zadatak);
                lista.add(odg);
            }
        }
        return lista;
    }

    // Getteri i setteri
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

    public List<String> getOdgovori() {
        return odgovori;
    }
    public void setOdgovori(List<String> odgovori) {
        this.odgovori = odgovori;
    }

    public Long getEvaluacijaZnanja_id() {
        return evaluacijaZnanja_id;
    }
    public void setEvaluacijaZnanja_id(Long evaluacijaZnanja_id) {
        this.evaluacijaZnanja_id = evaluacijaZnanja_id;
    }
}
