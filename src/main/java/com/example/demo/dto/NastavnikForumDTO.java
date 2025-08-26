package com.example.demo.dto;

public class NastavnikForumDTO {
    private Long nastavnikId;
    private String ime;
    private String prezime;
    private String jmbg;
    private Long ulogovaniKorisnikId;

    
    public NastavnikForumDTO(Long nastavnikId, String ime, String prezime, String jmbg, Long ulogovaniKorisnikId) {
        this.nastavnikId = nastavnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulogovaniKorisnikId = ulogovaniKorisnikId;
    }
    
    public NastavnikForumDTO() {}


	public Long getNastavnikId() {
		return nastavnikId;
	}


	public void setNastavnikId(Long nastavnikId) {
		this.nastavnikId = nastavnikId;
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


	public Long getUlogovaniKorisnikId() {
		return ulogovaniKorisnikId;
	}


	public void setUlogovaniKorisnikId(Long ulogovaniKorisnikId) {
		this.ulogovaniKorisnikId = ulogovaniKorisnikId;
	}

    
}