package com.example.demo.dto;

import java.util.Date;

public class IspitDTO {
    private String predmet;
    private Double brojBodova;
    private String rok;
    private Date datumPolaganja;

    public IspitDTO() {
    }

    public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	public Double getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(Double brojBodova) {
		this.brojBodova = brojBodova;
	}

	public String getRok() {
		return rok;
	}

	public void setRok(String rok) {
		this.rok = rok;
	}

	public Date getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

	public IspitDTO(String predmet, Double brojBodova, String rok, Date datumPolaganja) {
        this.predmet = predmet;
        this.brojBodova = brojBodova;
        this.rok = rok;
        this.datumPolaganja = datumPolaganja;
    }

    // Getteri i setteri...
}
