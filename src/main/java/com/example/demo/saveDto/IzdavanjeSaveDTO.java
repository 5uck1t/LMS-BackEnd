package com.example.demo.saveDto;

import com.example.demo.model.Izdavanje;

import java.util.Date;

public class IzdavanjeSaveDTO {

    private Long id;
    private Long udzbenik_id;
    private Long studentNaGodini_id;
    private Date datumIzdavanja;
    private Boolean obrisano = false;

    public IzdavanjeSaveDTO() {}

    public IzdavanjeSaveDTO(Long id, Long udzbenik_id, Long studentNaGodini_id, Date datumIzdavanja, Boolean obrisano) {
        this.id = id;
        this.udzbenik_id = udzbenik_id;
        this.studentNaGodini_id = studentNaGodini_id;
        this.datumIzdavanja = datumIzdavanja;
        this.obrisano = obrisano;
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUdzbenik_id() { return udzbenik_id; }
    public void setUdzbenik_id(Long udzbenik_id) { this.udzbenik_id = udzbenik_id; }
    public Long getStudentNaGodini_id() { return studentNaGodini_id; }
    public void setStudentNaGodini_id(Long studentNaGodini_id) { this.studentNaGodini_id = studentNaGodini_id; }
    public Date getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(Date datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Izdavanje toEntity() {
        Izdavanje e = new Izdavanje();
        e.setId(id);
        e.setDatumIzdavanja(datumIzdavanja);
        e.setObrisano(obrisano);
        return e;
    }
}
