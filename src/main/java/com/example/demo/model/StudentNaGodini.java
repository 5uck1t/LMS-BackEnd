package com.example.demo.model;

import com.example.demo.dto.StudentNaGodiniDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class StudentNaGodini {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datumUpisa;

    private Long brojIndeksa;

    @ManyToOne
    private Student student;

    @ManyToOne
    private GodinaStudija godinaStudija;

    @OneToMany(mappedBy = "studentNaGodini")
    private Set<PohadjanjePredmeta> pohadjanjaPredmeta;

    @ColumnDefault("false")
    private Boolean obrisano;

    public StudentNaGodini(Long brojIndeksa, Date datumUpisa, GodinaStudija godinaStudija, Long id, Boolean obrisano, Set<PohadjanjePredmeta> pohadjanjaPredmeta, Student student) {
        this.brojIndeksa = brojIndeksa;
        this.datumUpisa = datumUpisa;
        this.godinaStudija = godinaStudija;
        this.id = id;
        this.obrisano = obrisano;
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
        this.student = student;
    }

    public StudentNaGodini() {
    }

    public Long getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(Long brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public Date getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(Date datumUpisa) {
        this.datumUpisa = datumUpisa;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<PohadjanjePredmeta> getPohadjanjaPredmeta() {
        return pohadjanjaPredmeta;
    }

    public void setPohadjanjaPredmeta(Set<PohadjanjePredmeta> pohadjanjaPredmeta) {
        this.pohadjanjaPredmeta = pohadjanjaPredmeta;
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

    public StudentNaGodiniDTO toDto() {
        return new StudentNaGodiniDTO(this.id, this.datumUpisa, this.brojIndeksa, this.student.toDto(), this.godinaStudija.toDto(), null, this.obrisano);
    }
}
