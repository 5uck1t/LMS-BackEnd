package com.example.demo.saveDto;

import com.example.demo.model.StudentNaGodini;

import java.util.Date;

public class StudentNaGodiniSaveDTO {

    private Long id;
    private Date datumUpisa;
    private Long brojIndeksa;
    private Long student_id;
    private Long godinaStudija_id;
    private Boolean obrisano = false;

    public StudentNaGodiniSaveDTO(Long id, Date datumUpisa, Long brojIndeksa, Long student_id, Long godinaStudija_id, Boolean obrisano) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.student_id = student_id;
        this.godinaStudija_id = godinaStudija_id;
        this.obrisano = obrisano;
    }

    public StudentNaGodiniSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public Long getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(Long brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getGodinaStudija_id() {
        return godinaStudija_id;
    }

    public void setGodinaStudija_id(Long godinaStudija_id) {
        this.godinaStudija_id = godinaStudija_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public StudentNaGodini toEntity() {
        StudentNaGodini e = new StudentNaGodini();
        e.setId(id);
        e.setDatumUpisa(datumUpisa);
        e.setBrojIndeksa(brojIndeksa);
        e.setPohadjanjaPredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }
}
