package com.example.demo.dto;

import com.example.demo.model.Dopunjavanje;
import com.example.demo.model.Udzbenik;
import com.example.demo.saveDto.DopunjavanjeSaveDTO;

import java.util.Date;

public class DopunjavanjeDTO {

    private Long id;
    private UdzbenikDTO udzbenik;
    private Date datum;
    private Integer kolicina;
    private String napomena;
    private Boolean obrisano = false;

    public DopunjavanjeDTO() {}

    public DopunjavanjeDTO(Long id, UdzbenikDTO udzbenik, Date datum, Integer kolicina, String napomena, Boolean obrisano) {
        this.id = id;
        this.udzbenik = udzbenik;
        this.datum = datum;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.obrisano = obrisano;
    }

    public DopunjavanjeDTO(Dopunjavanje e) {
        this(e.getId(),
             e.getUdzbenik() != null ? e.getUdzbenik().toDto() : null,
             e.getDatum(),
             e.getKolicina(),
             e.getNapomena(),
             e.getObrisano());
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UdzbenikDTO getUdzbenik() { return udzbenik; }
    public void setUdzbenik(UdzbenikDTO udzbenik) { this.udzbenik = udzbenik; }
    public Date getDatum() { return datum; }
    public void setDatum(Date datum) { this.datum = datum; }
    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }
    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Dopunjavanje toEntity(Udzbenik udzbenik) {
        Dopunjavanje e = new Dopunjavanje();
        e.setId(id);
        e.setUdzbenik(udzbenik);
        e.setDatum(datum);
        e.setKolicina(kolicina);
        e.setNapomena(napomena);
        e.setObrisano(obrisano);
        return e;
    }

    public DopunjavanjeSaveDTO toSaveDto() {
        DopunjavanjeSaveDTO s = new DopunjavanjeSaveDTO();
        s.setId(id);
        s.setUdzbenik_id(udzbenik != null ? udzbenik.getId() : null);
        s.setDatum(datum);
        s.setKolicina(kolicina);
        s.setNapomena(napomena);
        s.setObrisano(obrisano);
        return s;
    }
}
