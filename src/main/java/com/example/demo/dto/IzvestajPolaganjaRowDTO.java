package com.example.demo.dto;

import java.util.Date;

public class IzvestajPolaganjaRowDTO {
    private Long polaganjeId;
    private Date datum;
    private String student;   // "Ime Prezime"
    private String indeks;    // npr. "RN 12/2021" (ako imaš u modelu)
    private Double bodovi;
    private Integer ocena;    // izračunato iz bodova

    public Long getPolaganjeId() { return polaganjeId; }
    public void setPolaganjeId(Long polaganjeId) { this.polaganjeId = polaganjeId; }

    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }

    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }

    public String getIndeks() { return indeks; }
    public void setIndeks(String indeks) { this.indeks = indeks; }

    public Double getBodovi() { return bodovi; }
    public void setBodovi(Double bodovi) { this.bodovi = bodovi; }

    public Integer getOcena() { return ocena; }
    public void setOcena(Integer ocena) { this.ocena = ocena; }
}
