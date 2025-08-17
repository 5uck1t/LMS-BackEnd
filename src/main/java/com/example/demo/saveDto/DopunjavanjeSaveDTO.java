package com.example.demo.saveDto;

import com.example.demo.model.Dopunjavanje;

import java.util.Date;

public class DopunjavanjeSaveDTO {

    private Long id;
    private Long udzbenik_id;
    private Date datum;
    private Integer kolicina;
    private String napomena;
    private Boolean obrisano = false;

    public DopunjavanjeSaveDTO() {}

    public DopunjavanjeSaveDTO(Long id, Long udzbenik_id, Date datum, Integer kolicina, String napomena, Boolean obrisano) {
        this.id = id;
        this.udzbenik_id = udzbenik_id;
        this.datum = datum;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.obrisano = obrisano;
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUdzbenik_id() { return udzbenik_id; }
    public void setUdzbenik_id(Long udzbenik_id) { this.udzbenik_id = udzbenik_id; }
    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }
    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }
    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Dopunjavanje toEntity() {
        Dopunjavanje e = new Dopunjavanje();
        e.setId(id);
        e.setDatum(datum);
        e.setKolicina(kolicina);
        e.setNapomena(napomena);
        e.setObrisano(obrisano);
        return e;
    }
}
