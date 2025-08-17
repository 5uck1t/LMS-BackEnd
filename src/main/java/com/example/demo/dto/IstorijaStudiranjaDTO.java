package com.example.demo.dto;

import java.util.List;

public class IstorijaStudiranjaDTO {
    private Long studentId;
    private String ime;
    private String prezime;
    private List<IstorijaPredmetaDTO> predmeti;

    public IstorijaStudiranjaDTO() {}

    public IstorijaStudiranjaDTO(Long studentId, String ime, String prezime, List<IstorijaPredmetaDTO> predmeti) {
        this.studentId = studentId;
        this.ime = ime;
        this.prezime = prezime;
        this.predmeti = predmeti;
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

	public List<IstorijaPredmetaDTO> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<IstorijaPredmetaDTO> predmeti) {
		this.predmeti = predmeti;
	}
}
