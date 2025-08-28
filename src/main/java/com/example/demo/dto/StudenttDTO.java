package com.example.demo.dto;

public class StudenttDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String jmbg;
    private Long brojIndeksa; // uzimamo ga iz StudentNaGodini (najnoviji)
    private Long ulogovaniKorisnik_id;

    public StudenttDTO(Long id, String ime, String prezime, String jmbg, Long brojIndeksa, Long ulogovaniKorisnik_id) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.brojIndeksa = brojIndeksa;
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

    public Long getUlogovaniKorisnik_id() {
        return ulogovaniKorisnik_id;
    }

    public void setUlogovaniKorisnik_id(Long ulogovaniKorisnik_id) {
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
    }
}
