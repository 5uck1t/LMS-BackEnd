package com.example.demo.dto;

import com.example.demo.model.Stranica;
import com.example.demo.saveDto.StranicaSaveDTO;

public class StranicaDTO {

    private Long id;
    private Integer brojStranice;
    private String sadrzaj;
    private UdzbenikDTO udzbenik;
    private Boolean obrisano = false;

    public StranicaDTO(Long id, Integer brojStranice, String sadrzaj, UdzbenikDTO udzbenik,Boolean obrisano) {
        this.id = id;
        this.brojStranice = brojStranice;
        this.sadrzaj = sadrzaj;
        this.udzbenik = udzbenik;
        this.obrisano = obrisano;
    }

    public StranicaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrojStranice() {
        return brojStranice;
    }

    public void setBrojStranice(Integer brojStranice) {
        this.brojStranice = brojStranice;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public UdzbenikDTO getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(UdzbenikDTO udzbenik) {
        this.udzbenik = udzbenik;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Stranica toEntity(){
        Stranica stranica = new Stranica();
        stranica.setId(this.id);
        stranica.setBrojStranice(this.brojStranice);
        stranica.setSadrzaj(this.sadrzaj);
        stranica.setUdzbenik(this.udzbenik.toEntity());
        stranica.setObrisano(obrisano);

        return stranica;
    }

    public StranicaSaveDTO toSaveDto(){
        StranicaSaveDTO e = new StranicaSaveDTO();

        e.setId(id);
        e.setBrojStranice(brojStranice);
        e.setSadrzaj(sadrzaj);
        e.setUdzbenik_id(udzbenik.getId());
        e.setObrisano(obrisano);

        return e;
    }
}
