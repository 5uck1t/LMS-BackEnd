package com.example.demo.model;

import com.example.demo.dto.StudentDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Osoba osoba;

    @OneToMany(mappedBy = "student")
    private Set<StudentNaGodini> studentNaGodini;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Student(Long id, Boolean obrisano, Osoba osoba, Set<StudentNaGodini> studentNaGodini) {
        this.id = id;
        this.obrisano = obrisano;
        this.osoba = osoba;
        this.studentNaGodini = studentNaGodini;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public Set<StudentNaGodini> getStudentNaGodini() {
        return studentNaGodini;
    }

    public void setStudentNaGodini(Set<StudentNaGodini> studentNaGodini) {
        this.studentNaGodini = studentNaGodini;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public StudentDTO toDto() {
        return new StudentDTO(this.id, this.osoba.toDto(), null, this.obrisano);
    }
}
