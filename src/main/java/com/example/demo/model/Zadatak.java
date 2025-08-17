package com.example.demo.model;

import com.example.demo.dto.OdgovorDTO;
import com.example.demo.dto.ZadatakDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Zadatak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pitanje;

    @ManyToOne
    private EvaluacijaZnanja evaluacijaZnanja;

    @OneToMany(mappedBy = "zadatak")
    private Set<Odgovor> odgovori;


    @ColumnDefault("false")
    private Boolean obrisano;

    public Zadatak(Long id, String pitanje, EvaluacijaZnanja evaluacijaZnanja, Set<Odgovor> odgovori, Boolean obrisano) {
        this.id = id;
        this.pitanje = pitanje;
        this.evaluacijaZnanja = evaluacijaZnanja;
        this.obrisano = obrisano;
        this.odgovori = odgovori;
    }

    public Zadatak() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
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

    public Set<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(Set<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public ZadatakDTO toDto(){
        List<OdgovorDTO> odgovorDTOs = (odgovori == null) ? 
            new ArrayList<>() : 
            odgovori.stream()
                .filter(o -> o.getObrisano() == null || !o.getObrisano()) // filtriraj obrisane ako treba
                .map(Odgovor::toDto)
                .collect(Collectors.toList());

        return new ZadatakDTO(this.id, this.pitanje, this.getEvaluacijaZnanja().toDto(), this.obrisano, odgovorDTOs);
    }

}
