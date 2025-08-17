package com.example.demo.saveDto;

import com.example.demo.model.Student;

public class StudentSaveDTO {

    private Long id;
    private Long osoba_id;
    private Boolean obrisano = false;

    public StudentSaveDTO(Long id, Long osoba_id, Boolean obrisano) {
        this.id = id;
        this.osoba_id = osoba_id;
        this.obrisano = obrisano;
    }

    public StudentSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOsoba_id() {
        return osoba_id;
    }

    public void setOsoba_id(Long osoba_id) {
        this.osoba_id = osoba_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Student toEntity() {
        Student e = new Student();
        e.setId(id);
        e.setStudentNaGodini(null);
        e.setObrisano(obrisano);
        return e;
    }
}
