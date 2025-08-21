package com.example.demo.saveDto;


import com.example.demo.model.Stranica;
import com.example.demo.model.StudentNaGodini;

public class StranicaSaveDTO {

    private Long id;
    private Integer brojStranice;
    private String sadrzaj;
    private Long udzbenik_id;
    private Boolean obrisano = false;

    public StranicaSaveDTO(Long id, Integer brojStranice, String sadrzaj, Long udzbenik_id,Boolean obrisano) {
        this.id = id;
        this.brojStranice = brojStranice;
        this.sadrzaj = sadrzaj;
        this.udzbenik_id = udzbenik_id;
        this.obrisano = obrisano;
    }

    public StranicaSaveDTO() {
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

    public Long getUdzbenik_id() {
        return udzbenik_id;
    }

    public void setUdzbenik_id(Long udzbenik_id) {
        this.udzbenik_id = udzbenik_id;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public Stranica toEntity() {
        Stranica e = new Stranica();
        e.setId(id);
        e.setBrojStranice(brojStranice);
        e.setSadrzaj(sadrzaj);
        e.setObrisano(obrisano);
        return e;
    }
}
