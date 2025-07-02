package com.example.demo.saveDto;

import com.example.demo.dto.NastavnikHasZvanjeDTO;
import com.example.demo.dto.NaucnaOblastDTO;
import com.example.demo.dto.TipZvanjaDTO;
import com.example.demo.model.Zvanje;

import java.util.Date;
import java.util.Set;

public class ZvanjeSaveDTO {

    private Long id;
    private Date datumIzbora;
    private Date datumPrestanka;
    private Long tipZvanja_id;
    private Long naucnaOblast_id;
    private Boolean obrisano = false;

    public ZvanjeSaveDTO(Long id, Date datumIzbora, Date datumPrestanka, Long tipZvanja_id, Long naucnaOblast_id, Boolean obrisano) {
        this.id = id;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.tipZvanja_id = tipZvanja_id;
        this.naucnaOblast_id = naucnaOblast_id;
        this.obrisano = obrisano;
    }

    public ZvanjeSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(Date datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    public Date getDatumPrestanka() {
        return datumPrestanka;
    }

    public void setDatumPrestanka(Date datumPrestanka) {
        this.datumPrestanka = datumPrestanka;
    }

    public Long getTipZvanja_id() {
        return tipZvanja_id;
    }

    public void setTipZvanja_id(Long tipZvanja_id) {
        this.tipZvanja_id = tipZvanja_id;
    }

    public Long getNaucnaOblast_id() {
        return naucnaOblast_id;
    }

    public void setNaucnaOblast_id(Long naucnaOblast_id) {
        this.naucnaOblast_id = naucnaOblast_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Zvanje toEntity() {
        Zvanje e = new Zvanje();
        e.setId(id);
        e.setNastavnikHasZvanje(null);
        e.setDatumIzbora(datumIzbora);
        e.setDatumPrestanka(datumPrestanka);
        e.setObrisano(obrisano);
        return e;
    }

}
