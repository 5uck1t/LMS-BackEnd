package com.example.demo.model;

import com.example.demo.dto.PredmetDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "predmet")
    private Set<RealizacijaPredmeta> realizacijePredmeta;

    private  String naziv;

    private Integer espb;

    private Boolean obavezan;

    private Integer brojPredavanja;

    private Integer brojVezbi;

    private String drugiObliciNastave;

    private String istrazivackiRad;

    private String ostaliCasovi;

    private String brojSemestara;

    @OneToMany(mappedBy = "predmet")
    private Set<Silabus> silabus;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Predmet(Integer brojPredavanja, String brojSemestara, Integer brojVezbi, String drugiObliciNastave, Integer espb, Long id, String istrazivackiRad, String naziv, Boolean obavezan, Boolean obrisano, String ostaliCasovi, Set<RealizacijaPredmeta> realizacijePredmeta, Set<Silabus> silabus) {
        this.brojPredavanja = brojPredavanja;
        this.brojSemestara = brojSemestara;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.espb = espb;
        this.id = id;
        this.istrazivackiRad = istrazivackiRad;
        this.naziv = naziv;
        this.obavezan = obavezan;
        this.obrisano = obrisano;
        this.ostaliCasovi = ostaliCasovi;
        this.realizacijePredmeta = realizacijePredmeta;
        this.silabus = silabus;
    }

    public Predmet() {
    }

    public Integer getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(Integer brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
    }

    public String getBrojSemestara() {
        return brojSemestara;
    }

    public void setBrojSemestara(String brojSemestara) {
        this.brojSemestara = brojSemestara;
    }

    public Integer getBrojVezbi() {
        return brojVezbi;
    }

    public void setBrojVezbi(Integer brojVezbi) {
        this.brojVezbi = brojVezbi;
    }

    public String getDrugiObliciNastave() {
        return drugiObliciNastave;
    }

    public void setDrugiObliciNastave(String drugiObliciNastave) {
        this.drugiObliciNastave = drugiObliciNastave;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIstrazivackiRad() {
        return istrazivackiRad;
    }

    public void setIstrazivackiRad(String istrazivackiRad) {
        this.istrazivackiRad = istrazivackiRad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getObavezan() {
        return obavezan;
    }

    public void setObavezan(Boolean obavezan) {
        this.obavezan = obavezan;
    }

    public String getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(String ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Set<Silabus> getSilabus() {
        return silabus;
    }

    public void setSilabus(Set<Silabus> silabus) {
        this.silabus = silabus;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PredmetDTO toDto() {
        return new PredmetDTO(this.id, null, this.naziv, this.espb, this.obavezan, this.brojPredavanja, this.brojVezbi, this.drugiObliciNastave, this.istrazivackiRad, this.ostaliCasovi, this.brojSemestara, null, this.obrisano);
    }
}
