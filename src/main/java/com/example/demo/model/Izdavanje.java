package com.example.demo.model;

import com.example.demo.dto.IzdavanjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
public class Izdavanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Udzbenik udzbenik;

    @ManyToOne
    private StudentNaGodini student;

    @Temporal(TemporalType.DATE)
    private Date datumIzdavanja;


    @ColumnDefault("false")
    private Boolean obrisano;

    public Izdavanje() {}

    public Izdavanje(Long id, Udzbenik udzbenik, StudentNaGodini student, Date datumIzdavanja,  Boolean obrisano) {
        this.id = id;
        this.udzbenik = udzbenik;
        this.student = student;
        this.datumIzdavanja = datumIzdavanja;
   
        this.obrisano = obrisano;
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Udzbenik getUdzbenik() { return udzbenik; }
    public void setUdzbenik(Udzbenik udzbenik) { this.udzbenik = udzbenik; }
    public StudentNaGodini getStudent() { return student; }
    public void setStudent(StudentNaGodini student) { this.student = student; }
    public Date getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(Date datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }
   
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public IzdavanjeDTO toDto() {
        return new IzdavanjeDTO(
                this.id,
                this.udzbenik != null ? this.udzbenik.toDto() : null,
                this.student != null ? this.student.toDto() : null,
                this.datumIzdavanja,
                this.obrisano
        );
    }
}
