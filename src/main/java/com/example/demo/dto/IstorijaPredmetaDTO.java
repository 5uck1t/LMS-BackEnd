package com.example.demo.dto;

public class IstorijaPredmetaDTO {
    private String nazivPredmeta;
    private int brojPolaganja;
    private double ukupnoPoena;
    private int espb;
    private Integer ocena;

    public IstorijaPredmetaDTO() {}

    public IstorijaPredmetaDTO(String nazivPredmeta, int brojPolaganja, double ukupnoPoena, int espb, Integer ocena) {
        this.nazivPredmeta = nazivPredmeta;
        this.brojPolaganja = brojPolaganja;
        this.ukupnoPoena = ukupnoPoena;
        this.espb = espb;
        this.ocena = ocena;
    }

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public int getBrojPolaganja() {
		return brojPolaganja;
	}

	public void setBrojPolaganja(int brojPolaganja) {
		this.brojPolaganja = brojPolaganja;
	}

	public double getUkupnoPoena() {
		return ukupnoPoena;
	}

	public void setUkupnoPoena(double ukupnoPoena) {
		this.ukupnoPoena = ukupnoPoena;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public Integer getOcena() {
		return ocena;
	}

	public void setOcena(Integer ocena) {
		this.ocena = ocena;
	}
}
