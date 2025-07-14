package com.example.demo.dto;

public class StudentSearchDTO {
    private String ime;
    private String prezime;
    private Long brojIndeksa;
    private Integer godinaUpisa;
    private Double prosecnaOcena;

    public StudentSearchDTO() {}

    public StudentSearchDTO(String ime, String prezime, Long brojIndeksa, Integer godinaUpisa, Double prosecnaOcena) {
        this.ime = ime;
        this.prezime = prezime;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
        this.prosecnaOcena = prosecnaOcena;
    }

    public String getIme() {
        return ime;
    }
    public void setIme(String ime) {
        this.ime = (ime != null && ime.trim().isEmpty()) ? null : ime;
    }

    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String prezime) {
        this.prezime = (prezime != null && prezime.trim().isEmpty()) ? null : prezime;
    }

    public Long getBrojIndeksa() {
        return brojIndeksa;
    }
    public void setBrojIndeksa(Long brojIndeksa) {
        this.brojIndeksa = (brojIndeksa != null && brojIndeksa == 0) ? null : brojIndeksa;
    }

    public Integer getGodinaUpisa() {
        return godinaUpisa;
    }
    public void setGodinaUpisa(Integer godinaUpisa) {
        this.godinaUpisa = (godinaUpisa != null && godinaUpisa == 0) ? null : godinaUpisa;
    }

    public Double getProsecnaOcena() {
        return prosecnaOcena;
    }
    public void setProsecnaOcena(Double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    @Override
    public String toString() {
        return "StudentSearchDTO{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brojIndeksa=" + brojIndeksa +
                ", godinaUpisa=" + godinaUpisa +
                ", prosecnaOcena=" + prosecnaOcena +
                '}';
    }
}
