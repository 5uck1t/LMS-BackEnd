package com.example.demo.saveDto;

import com.example.demo.dto.RealizacijaPredmetaDTO;
import com.example.demo.dto.SilabusDTO;
import com.example.demo.model.Predmet;

import java.util.Set;

public class PredmetSaveDTO {

    private Long id;
    private String naziv;
    private Integer espb;
    private Boolean obavezan;
    private Integer brojPredavanja;
    private Integer brojVezbi;
    private String drugiObliciNastave;
    private String istrazivackiRad;
    private String ostaliCasovi;
    private String brojSemestara;
    private Boolean obrisano = false;

    public PredmetSaveDTO(Long id, String naziv, Integer espb, Boolean obavezan, Integer brojPredavanja, Integer brojVezbi, String drugiObliciNastave, String istrazivackiRad, String ostaliCasovi, String brojSemestara, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.espb = espb;
        this.obavezan = obavezan;
        this.brojPredavanja = brojPredavanja;
        this.brojVezbi = brojVezbi;
        this.drugiObliciNastave = drugiObliciNastave;
        this.istrazivackiRad = istrazivackiRad;
        this.ostaliCasovi = ostaliCasovi;
        this.brojSemestara = brojSemestara;
        this.obrisano = obrisano;
    }

    public PredmetSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public Boolean getObavezan() {
        return obavezan;
    }

    public void setObavezan(Boolean obavezan) {
        this.obavezan = obavezan;
    }

    public Integer getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(Integer brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
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

    public String getIstrazivackiRad() {
        return istrazivackiRad;
    }

    public void setIstrazivackiRad(String istrazivackiRad) {
        this.istrazivackiRad = istrazivackiRad;
    }

    public String getOstaliCasovi() {
        return ostaliCasovi;
    }

    public void setOstaliCasovi(String ostaliCasovi) {
        this.ostaliCasovi = ostaliCasovi;
    }

    public String getBrojSemestara() {
        return brojSemestara;
    }

    public void setBrojSemestara(String brojSemestara) {
        this.brojSemestara = brojSemestara;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Predmet toEntity() {
        Predmet e = new Predmet();
        e.setId(id);
        e.setRealizacijePredmeta(null);
        e.setNaziv(naziv);
        e.setEspb(espb);
        e.setObavezan(obavezan);
        e.setBrojPredavanja(brojPredavanja);
        e.setBrojVezbi(brojVezbi);
        e.setDrugiObliciNastave(drugiObliciNastave);
        e.setIstrazivackiRad(istrazivackiRad);
        e.setOstaliCasovi(ostaliCasovi);
        e.setBrojSemestara(brojSemestara);
        e.setSilabus(null);
        e.setObrisano(obrisano);
        return e;
    }
}
