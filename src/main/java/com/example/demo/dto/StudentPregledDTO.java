package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class StudentPregledDTO {

    // 1. Osnovni podaci
    private String ime;
    private String prezime;
    private String jmbg;
    private Long brojIndeksa;
    private String studijskiProgram;

    // 2. Statistika
    private double prosecnaOcena;
    private int ukupnoOsvojenihEspb;

    // 3. Upisi
    private List<UpisDTO> upisi;

    // 4. Položeni ispiti
    private List<IspitDTO> polozeniIspiti;

    // 5. Neuspešni ispiti
    private List<IspitDTO> neuspesnaPolaganja;

    // --- Constructors, Getters, Setters ---

    public StudentPregledDTO() {
    }

    public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public Long getBrojIndeksa() {
		return brojIndeksa;
	}

	public void setBrojIndeksa(Long brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}

	public String getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(String studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public int getUkupnoOsvojenihEspb() {
		return ukupnoOsvojenihEspb;
	}

	public void setUkupnoOsvojenihEspb(int ukupnoOsvojenihEspb) {
		this.ukupnoOsvojenihEspb = ukupnoOsvojenihEspb;
	}

	public List<UpisDTO> getUpisi() {
		return upisi;
	}

	public void setUpisi(List<UpisDTO> upisi) {
		this.upisi = upisi;
	}

	public List<IspitDTO> getPolozeniIspiti() {
		return polozeniIspiti;
	}

	public void setPolozeniIspiti(List<IspitDTO> polozeniIspiti) {
		this.polozeniIspiti = polozeniIspiti;
	}

	public List<IspitDTO> getNeuspesnaPolaganja() {
		return neuspesnaPolaganja;
	}

	public void setNeuspesnaPolaganja(List<IspitDTO> neuspesnaPolaganja) {
		this.neuspesnaPolaganja = neuspesnaPolaganja;
	}

	public StudentPregledDTO(String ime, String prezime, String jmbg, Long brojIndeksa, String studijskiProgram,
                             double prosecnaOcena, int ukupnoOsvojenihEspb,
                             List<UpisDTO> upisi, List<IspitDTO> polozeniIspiti, List<IspitDTO> neuspesnaPolaganja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.brojIndeksa = brojIndeksa;
        this.studijskiProgram = studijskiProgram;
        this.prosecnaOcena = prosecnaOcena;
        this.ukupnoOsvojenihEspb = ukupnoOsvojenihEspb;
        this.upisi = upisi;
        this.polozeniIspiti = polozeniIspiti;
        this.neuspesnaPolaganja = neuspesnaPolaganja;
    }

    // Getteri i setteri...
}
