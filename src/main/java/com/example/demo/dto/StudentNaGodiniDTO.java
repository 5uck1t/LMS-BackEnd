package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.StudentNaGodini;
import com.example.demo.saveDto.StudentNaGodiniSaveDTO;

public class StudentNaGodiniDTO {

    private Long id;
    private Date datumUpisa;
    private Long brojIndeksa;
    private StudentDTO student;
    private GodinaStudijaDTO godinaStudija;
    private Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta;
    private Boolean obrisano = false;

    public StudentNaGodiniDTO() {
    }

    public StudentNaGodiniDTO(Long id, Date datumUpisa, Long brojIndeksa, StudentDTO student, GodinaStudijaDTO godinaStudija, Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta, Boolean obrisano) {
        this.id = id;
        this.datumUpisa = datumUpisa;
        this.brojIndeksa = brojIndeksa;
        this.student = student;
        this.godinaStudija = godinaStudija;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDatumUpisa() { return datumUpisa; }
    public void setDatumUpisa(Date datumUpisa) { this.datumUpisa = datumUpisa; }

    public Long getBrojIndeksa() { return brojIndeksa; }
    public void setBrojIndeksa(Long brojIndeksa) { this.brojIndeksa = brojIndeksa; }

    public StudentDTO getStudent() { return student; }
    public void setStudent(StudentDTO student) { this.student = student; }

    public GodinaStudijaDTO getGodinaStudija() { return godinaStudija; }
    public void setGodinaStudija(GodinaStudijaDTO godinaStudija) { this.godinaStudija = godinaStudija; }

    public Set<PohadjanjePredmetaDTO> getPohadjanjaPredmeta() { return pohadjanjaPredmeta; }
    public void setPohadjanjaPredmeta(Set<PohadjanjePredmetaDTO> pohadjanjaPredmeta) { this.pohadjanjaPredmeta = pohadjanjaPredmeta; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public StudentNaGodini toEntity() {
        StudentNaGodini e = new StudentNaGodini();
        e.setId(id);
        e.setDatumUpisa(datumUpisa);
        e.setBrojIndeksa(brojIndeksa);
        e.setStudent(student.toEntity());
        e.setGodinaStudija(godinaStudija.toEntity());
        e.setPohadjanjaPredmeta(null);
        e.setObrisano(obrisano);
        return e;
    }

    public StudentNaGodiniSaveDTO toSaveDto() {
        StudentNaGodiniSaveDTO e = new StudentNaGodiniSaveDTO();
        e.setId(id);
        e.setDatumUpisa(datumUpisa);
        e.setBrojIndeksa(brojIndeksa);
        e.setStudent_id(student.getId());
        e.setGodinaStudija_id(godinaStudija.getId());
        e.setObrisano(obrisano);
        return e;
    }

}