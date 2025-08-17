package com.example.demo.dto;

public class StudentNaPredmetuDTO {
    private Long studentId;
    private String ime;
    private String prezime;
    private String jmbg;
    private Long brojIndeksa;

    public StudentNaPredmetuDTO(Long studentId, String ime, String prezime, String jmbg, Long brojIndeksa) {
        this.studentId = studentId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.brojIndeksa = brojIndeksa;
    }

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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

    
}
