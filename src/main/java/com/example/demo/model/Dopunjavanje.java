package com.example.demo.model;

import com.example.demo.dto.DopunjavanjeDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
public class Dopunjavanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Udzbenik udzbenik;

    @Temporal(TemporalType.DATE)
    private Date datum;

    private Integer kolicina;

    private String napomena;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Dopunjavanje() {}

    public Dopunjavanje(Long id, Udzbenik udzbenik, Date datum, Integer kolicina, String napomena, Boolean obrisano) {
        this.id = id;
        this.udzbenik = udzbenik;
        this.datum = datum;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.obrisano = obrisano;
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Udzbenik getUdzbenik() { return udzbenik; }
    public void setUdzbenik(Udzbenik udzbenik) { this.udzbenik = udzbenik; }
    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }
    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }
    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public DopunjavanjeDTO toDto() {
        return new DopunjavanjeDTO(
                this.id,
                this.udzbenik != null ? this.udzbenik.toDto() : null,
                this.datum,
                this.kolicina,
                this.napomena,
                this.obrisano
        );
    }
}
