package com.example.demo.saveDto;

import com.example.demo.dto.AdresaDTO;
import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.StudentDTO;
import com.example.demo.model.Osoba;

public class OsobaSaveDTO {

    private Long id;
    private String jmbg;
    private String ime;
    private String prezime;
    private Long adresa_id;
    private Long nastavnik_id;
    private Long student_id;
    private Boolean obrisano = false;

    public OsobaSaveDTO(Long id, String jmbg, String ime, String prezime, Long adresa_id, Long nastavnik_id, Long student_id, Boolean obrisano) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa_id = adresa_id;
        this.nastavnik_id = nastavnik_id;
        this.student_id = student_id;
        this.obrisano = obrisano;
    }

    public OsobaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public Long getAdresa_id() {
        return adresa_id;
    }

    public void setAdresa_id(Long adresa_id) {
        this.adresa_id = adresa_id;
    }

    public Long getNastavnik_id() {
        return nastavnik_id;
    }

    public void setNastavnik_id(Long nastavnik_id) {
        this.nastavnik_id = nastavnik_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Osoba toEntity() {
        Osoba e = new Osoba();
        e.setId(id);
        e.setJmbg(jmbg);
        e.setIme(ime);
        e.setPrezime(prezime);
        e.setObrisano(obrisano);
        return e;
    }
}
