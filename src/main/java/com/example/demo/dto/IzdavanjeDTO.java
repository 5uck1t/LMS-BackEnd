package com.example.demo.dto;

import com.example.demo.model.Izdavanje;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.model.Udzbenik;
import com.example.demo.saveDto.IzdavanjeSaveDTO;

import java.util.Date;

public class IzdavanjeDTO {

    private Long id;
    private UdzbenikDTO udzbenik;
    private StudentNaGodiniDTO student;
    private Date datumIzdavanja;
    private Boolean obrisano = false;

    public IzdavanjeDTO() {}

    public IzdavanjeDTO(Long id, UdzbenikDTO udzbenik, StudentNaGodiniDTO student, Date datumIzdavanja,  Boolean obrisano) {
        this.id = id;
        this.udzbenik = udzbenik;
        this.student = student;
        this.datumIzdavanja = datumIzdavanja;

        this.obrisano = obrisano;
    }

    public IzdavanjeDTO(Izdavanje e) {
        this(e.getId(),
             e.getUdzbenik() != null ? e.getUdzbenik().toDto() : null,
             e.getStudent() != null ? e.getStudent().toDto() : null,
             e.getDatumIzdavanja(),
             e.getObrisano());
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UdzbenikDTO getUdzbenik() { return udzbenik; }
    public void setUdzbenik(UdzbenikDTO udzbenik) { this.udzbenik = udzbenik; }
    public StudentNaGodiniDTO getStudent() { return student; }
    public void setStudent(StudentNaGodiniDTO student) { this.student = student; }
    public Date getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(Date datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Izdavanje toEntity(Udzbenik udzbenik, StudentNaGodini student) {
        Izdavanje e = new Izdavanje();
        e.setId(id);
        e.setUdzbenik(udzbenik);
        e.setStudent(student);
        e.setDatumIzdavanja(datumIzdavanja);
        e.setObrisano(obrisano);
        return e;
    }

    public IzdavanjeSaveDTO toSaveDto() {
        IzdavanjeSaveDTO s = new IzdavanjeSaveDTO();
        s.setId(id);
        s.setUdzbenik_id(udzbenik != null ? udzbenik.getId() : null);
        s.setStudentNaGodini_id(student != null ? student.getId() : null);
        s.setDatumIzdavanja(datumIzdavanja);
        s.setObrisano(obrisano);
        return s;
    }
}
