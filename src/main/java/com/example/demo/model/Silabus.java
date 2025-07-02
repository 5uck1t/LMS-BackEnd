package com.example.demo.model;

import com.example.demo.dto.SilabusDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Silabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String opis;

    @ManyToOne
    private Predmet predmet;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Silabus(Long id, Boolean obrisano, String opis, Predmet predmet) {
        this.id = id;
        this.obrisano = obrisano;
        this.opis = opis;
        this.predmet = predmet;
    }

    public Silabus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public SilabusDTO toDto() {
        return new SilabusDTO(this.id, this.opis, this.predmet.toDto(), this.obrisano);
    }
}
