package com.example.demo.dto;

import java.util.*;

import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.saveDto.DodeljenoPravoPristupaSaveDTO;

public class DodeljenoPravoPristupaDTO {

    private Long id;
    private UlogovaniKorisnikDTO ulogovaniKorisnik;
    private PravoPristupaDTO pravoPristupa;
    private Boolean obrisano = false;

    public DodeljenoPravoPristupaDTO() {
    }

    public DodeljenoPravoPristupaDTO(Long id, UlogovaniKorisnikDTO ulogovaniKorisnik, PravoPristupaDTO pravoPristupa, Boolean obrisano) {
        this.id = id;
        this.ulogovaniKorisnik = ulogovaniKorisnik;
        this.pravoPristupa = pravoPristupa;
        this.obrisano = obrisano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public UlogovaniKorisnikDTO getUlogovaniKorisnik() { return ulogovaniKorisnik; }
    public void setUlogovaniKorisnik(UlogovaniKorisnikDTO ulogovaniKorisnik) { this.ulogovaniKorisnik = ulogovaniKorisnik; }

    public PravoPristupaDTO getPravoPristupa() { return pravoPristupa; }
    public void setPravoPristupa(PravoPristupaDTO pravoPristupa) { this.pravoPristupa = pravoPristupa; }

    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public DodeljenoPravoPristupa toEntity() {
        DodeljenoPravoPristupa e = new DodeljenoPravoPristupa();
        e.setId(id);
        e.setUlogovaniKorisnik(ulogovaniKorisnik.toEntity());
        e.setPravoPristupa(pravoPristupa.toEntity());
        e.setObrisano(obrisano);
        return e;
    }

    public DodeljenoPravoPristupaSaveDTO toSaveDto() {
        DodeljenoPravoPristupaSaveDTO e = new DodeljenoPravoPristupaSaveDTO();
        e.setId(id);
        e.setUlogovaniKorisnik_id(ulogovaniKorisnik.getId());
        e.setPravoPristupa_id(pravoPristupa.getId());
        e.setObrisano(obrisano);
        return e;
    }

}