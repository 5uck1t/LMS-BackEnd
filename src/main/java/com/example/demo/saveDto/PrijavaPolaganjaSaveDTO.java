package com.example.demo.saveDto;

import com.example.demo.dto.EvaluacijaZnanjaDTO;
import com.example.demo.dto.PolaganjeDTO;
import com.example.demo.model.PrijavaPolaganja;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class PrijavaPolaganjaSaveDTO {

    private Long id;

    private Long pohadjanjePredmeta_id;

    private Long polaganje_id;

    private Date datum = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());

    private Double brojBodova;

    private Boolean obrisano;

    public PrijavaPolaganjaSaveDTO(Long id, Long pohadjanjePredmeta_id, Long polaganje_id, Date datum, Double brojBodova, Boolean obrisano) {
        this.id = id;
        this.pohadjanjePredmeta_id = pohadjanjePredmeta_id;
        this.polaganje_id = polaganje_id;
        this.datum = datum;
        this.brojBodova = brojBodova;
        this.obrisano = obrisano;
    }

    public PrijavaPolaganjaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPohadjanjePredmeta_id() {
        return pohadjanjePredmeta_id;
    }

    public void setPohadjanjePredmeta_id(Long pohadjanjePredmeta_id) {
        this.pohadjanjePredmeta_id = pohadjanjePredmeta_id;
    }

    public Long getPolaganje_id() {
        return polaganje_id;
    }

    public void setPolaganje_id(Long polaganje_id) {
        this.polaganje_id = polaganje_id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Double brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public PrijavaPolaganja toEntity(){

        PrijavaPolaganja e = new PrijavaPolaganja();

        e.setId(id);
        e.setBrojBodova(brojBodova);
        e.setObrisano(obrisano);
        e.setDatum(datum);

        return e;
    }
}
