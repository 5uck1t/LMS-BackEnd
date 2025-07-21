package com.example.demo.model;

import com.example.demo.dto.PolaganjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
public class Polaganje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datum;

    @ManyToOne
    private Rok rok;

    @ManyToOne
    private EvaluacijaZnanja evaluacijaZnanja;

    @OneToMany(mappedBy = "polaganje")
    private Set<PrijavaPolaganja> prijave;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Polaganje(Long id, Date datum, Rok rok, EvaluacijaZnanja evaluacijaZnanja, Boolean obrisano) {
        this.id = id;
        this.datum = datum;
        this.rok = rok;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.obrisano = obrisano;
    }

    public Polaganje() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Rok getRok() {
        return rok;
    }

    public void setRok(Rok rok) {
        this.rok = rok;
    }

    public EvaluacijaZnanja getEvaluacijaZnanja() {
        return evaluacijaZnanja;
    }

    public void setEvaluacijaZnanja(EvaluacijaZnanja evaluacijaZnanja) {
        this.evaluacijaZnanja = evaluacijaZnanja;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PolaganjeDTO toDto(){
        PolaganjeDTO e = new PolaganjeDTO();

        e.setId(id);
        e.setDatum(datum);
        e.setRok(rok.toDto());
        e.setEvaluacijaZnanja(evaluacijaZnanja.toDto());
        e.setObrisano(obrisano);

        return e;
    }
}
