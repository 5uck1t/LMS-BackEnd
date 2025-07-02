package com.example.demo.saveDto;

import com.example.demo.dto.PravoPristupaDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.model.DodeljenoPravoPristupa;

public class DodeljenoPravoPristupaSaveDTO {

    private Long id;
    private Long ulogovaniKorisnik_id;
    private Long pravoPristupa_id;
    private Boolean obrisano = false;

    public DodeljenoPravoPristupaSaveDTO(Long id, Long ulogovaniKorisnik_id, Long pravoPristupa_id, Boolean obrisano) {
        this.id = id;
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
        this.pravoPristupa_id = pravoPristupa_id;
        this.obrisano = obrisano;
    }

    public DodeljenoPravoPristupaSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUlogovaniKorisnik_id() {
        return ulogovaniKorisnik_id;
    }

    public void setUlogovaniKorisnik_id(Long ulogovaniKorisnik_id) {
        this.ulogovaniKorisnik_id = ulogovaniKorisnik_id;
    }

    public Long getPravoPristupa_id() {
        return pravoPristupa_id;
    }

    public void setPravoPristupa_id(Long pravoPristupa_id) {
        this.pravoPristupa_id = pravoPristupa_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public DodeljenoPravoPristupa toEntity() {
        DodeljenoPravoPristupa e = new DodeljenoPravoPristupa();
        e.setId(id);
        e.setObrisano(obrisano);
        return e;
    }
}
