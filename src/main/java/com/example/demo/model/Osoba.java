package com.example.demo.model;

import com.example.demo.dto.OsobaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{13}")
    private String jmbg;

    private String ime;

    private String prezime;

    @ManyToOne
    private Adresa adresa;

    @OneToOne(mappedBy = "osoba")
    private Nastavnik nastavnik;

    @OneToOne(mappedBy = "osoba")
    private Student student;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Osoba(Adresa adresa, Long id, String ime, String jmbg, Nastavnik nastavnik, Boolean obrisano, String prezime, Student student) {
        this.adresa = adresa;
        this.id = id;
        this.ime = ime;
        this.jmbg = jmbg;
        this.nastavnik = nastavnik;
        this.obrisano = obrisano;
        this.prezime = prezime;
        this.student = student;
    }

    public Osoba() {
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public OsobaDTO toDto() {
        return new OsobaDTO(this.id, this.jmbg, this.ime, this.prezime, this.adresa.toDto(), null, null, this.obrisano);
    }
}
