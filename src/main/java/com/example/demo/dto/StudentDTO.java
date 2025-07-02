package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Student;
import com.example.demo.saveDto.StudentSaveDTO;

public class StudentDTO {

    private Long id;
    private OsobaDTO osoba;
    private Set<StudentNaGodiniDTO> studentNaGodini;
    private Boolean obrisano = false;

    public StudentDTO() {
    }

    public StudentDTO(Long id, OsobaDTO osoba, Set<StudentNaGodiniDTO> studentNaGodini, Boolean obrisano) {
        this.id = id;
        this.osoba = osoba;
        this.studentNaGodini = studentNaGodini;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OsobaDTO getOsoba() { return osoba; }
    public void setOsoba(OsobaDTO osoba) { this.osoba = osoba; }

    public Set<StudentNaGodiniDTO> getStudentNaGodini() { return studentNaGodini; }
    public void setStudentNaGodini(Set<StudentNaGodiniDTO> studentNaGodini) { this.studentNaGodini = studentNaGodini; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Student toEntity() {
        Student e = new Student();
        e.setId(id);
        e.setOsoba(osoba.toEntity());
        e.setStudentNaGodini(null);
        e.setObrisano(obrisano);
        return e;
    }

    public StudentSaveDTO toSaveDto() {
        StudentSaveDTO e = new StudentSaveDTO();
        e.setId(id);
        e.setOsoba_id(osoba.getId());
        e.setObrisano(obrisano);
        return e;
    }

}