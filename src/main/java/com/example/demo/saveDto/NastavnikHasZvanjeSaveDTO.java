package com.example.demo.saveDto;

import com.example.demo.dto.NastavnikDTO;
import com.example.demo.dto.ZvanjeDTO;
import com.example.demo.model.NastavnikHasZvanje;

public class NastavnikHasZvanjeSaveDTO {

    private Long id;
    private Long nastavnik_id;
    private Long zvanje_id;
    private Boolean obrisano = false;

    public NastavnikHasZvanjeSaveDTO(Long id, Long nastavnik_id, Long zvanje_id, Boolean obrisano) {
        this.id = id;
        this.nastavnik_id = nastavnik_id;
        this.zvanje_id = zvanje_id;
        this.obrisano = obrisano;
    }

    public NastavnikHasZvanjeSaveDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNastavnik_id() {
        return nastavnik_id;
    }

    public void setNastavnik_id(Long nastavnik_id) {
        this.nastavnik_id = nastavnik_id;
    }

    public Long getZvanje_id() {
        return zvanje_id;
    }

    public void setZvanje_id(Long zvanje_id) {
        this.zvanje_id = zvanje_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public NastavnikHasZvanje toEntity() {
        NastavnikHasZvanje e = new NastavnikHasZvanje();
        e.setId(id);
        e.setObrisano(obrisano);
        return e;
    }
}
