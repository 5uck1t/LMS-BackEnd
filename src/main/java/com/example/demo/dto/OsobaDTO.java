package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Osoba;
import com.example.demo.saveDto.OsobaSaveDTO;

public class OsobaDTO {

    private Long id;
    private String jmbg;
    private String ime;
    private String prezime;
    private AdresaDTO adresa;
    private NastavnikDTO nastavnik;
    private StudentDTO student;
    private Boolean obrisano = false;

    public OsobaDTO() {
    }

    public OsobaDTO(Long id, String jmbg, String ime, String prezime, AdresaDTO adresa, NastavnikDTO nastavnik, StudentDTO student, Boolean obrisano) {
        this.id = id;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.nastavnik = nastavnik;
        this.student = student;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJmbg() { return jmbg; }
    public void setJmbg(String jmbg) { this.jmbg = jmbg; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public AdresaDTO getAdresa() { return adresa; }
    public void setAdresa(AdresaDTO adresa) { this.adresa = adresa; }

    public NastavnikDTO getNastavnik() { return nastavnik; }
    public void setNastavnik(NastavnikDTO nastavnik) { this.nastavnik = nastavnik; }

    public StudentDTO getStudent() { return student; }
    public void setStudent(StudentDTO student) { this.student = student; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Osoba toEntity() {
        Osoba e = new Osoba();
        e.setId(id);
        e.setJmbg(jmbg);
        e.setIme(ime);
        e.setPrezime(prezime);
        e.setAdresa(adresa.toEntity());
        e.setNastavnik(nastavnik.toEntity());
        e.setStudent(student.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public OsobaSaveDTO toSaveDto() {
        OsobaSaveDTO e = new OsobaSaveDTO();
        e.setId(id);
        e.setJmbg(jmbg);
        e.setIme(ime);
        e.setPrezime(prezime);
        e.setAdresa_id(adresa.getId());
        e.setNastavnik_id(nastavnik.getId());
        e.setStudent_id(student.getId());
        e.setObrisano(obrisano);
        return e;
    }

}