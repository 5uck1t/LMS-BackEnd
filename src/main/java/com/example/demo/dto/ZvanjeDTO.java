package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.Zvanje;
import com.example.demo.saveDto.ZvanjeSaveDTO;

public class ZvanjeDTO {

    private Long id;
    private Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje;
    private Date datumIzbora;
    private Date datumPrestanka;
    private TipZvanjaDTO tipZvanja;
    private NaucnaOblastDTO naucnaOblast;
    private Boolean obrisano = false;

    public ZvanjeDTO() {
    }

    public ZvanjeDTO(Long id, Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje, Date datumIzbora, Date datumPrestanka, TipZvanjaDTO tipZvanja, NaucnaOblastDTO naucnaOblast, Boolean obrisano) {
        this.id = id;
        this.nastavnikHasZvanje = nastavnikHasZvanje;
        this.datumIzbora = datumIzbora;
        this.datumPrestanka = datumPrestanka;
        this.tipZvanja = tipZvanja;
        this.naucnaOblast = naucnaOblast;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Set<NastavnikHasZvanjeDTO> getNastavnikHasZvanje() { return nastavnikHasZvanje; }
    public void setNastavnikHasZvanje(Set<NastavnikHasZvanjeDTO> nastavnikHasZvanje) { this.nastavnikHasZvanje = nastavnikHasZvanje; }

    public Date getDatumIzbora() { return datumIzbora; }
    public void setDatumIzbora(Date datumIzbora) { this.datumIzbora = datumIzbora; }

    public Date getDatumPrestanka() { return datumPrestanka; }
    public void setDatumPrestanka(Date datumPrestanka) { this.datumPrestanka = datumPrestanka; }

    public TipZvanjaDTO getTipZvanja() { return tipZvanja; }
    public void setTipZvanja(TipZvanjaDTO tipZvanja) { this.tipZvanja = tipZvanja; }

    public NaucnaOblastDTO getNaucnaOblast() { return naucnaOblast; }
    public void setNaucnaOblast(NaucnaOblastDTO naucnaOblast) { this.naucnaOblast = naucnaOblast; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Zvanje toEntity() {
        Zvanje e = new Zvanje();
        e.setId(id);
        e.setNastavnikHasZvanje(null);
        e.setDatumIzbora(datumIzbora);
        e.setDatumPrestanka(datumPrestanka);
        e.setTipZvanja(tipZvanja.toEntity());
        e.setNaucnaOblast(naucnaOblast.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public ZvanjeSaveDTO toSaveDto() {
        ZvanjeSaveDTO e = new ZvanjeSaveDTO();
        e.setId(id);
        e.setDatumIzbora(datumIzbora);
        e.setDatumPrestanka(datumPrestanka);
        e.setObrisano(obrisano);
        return e;
    }

}