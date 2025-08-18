package com.example.demo.model;

import com.example.demo.dto.StranicaDTO;
import jakarta.persistence.*;

@Entity
public class Stranica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer brojStranice;

    @Lob
    private String sadrzaj; // text of the page

    @ManyToOne
    private Udzbenik udzbenik;

    private Boolean obrisano;

    public Stranica(Long id, Integer brojStranice, String sadrzaj, Udzbenik udzbenik,Boolean obrisano) {
        this.id = id;
        this.brojStranice = brojStranice;
        this.sadrzaj = sadrzaj;
        this.udzbenik = udzbenik;
        this.obrisano = obrisano;
    }

    public Stranica() {
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

    public Udzbenik getUdzbenik() {
        return udzbenik;
    }

    public void setUdzbenik(Udzbenik udzbenik) {
        this.udzbenik = udzbenik;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public StranicaDTO toDto() {
        StranicaDTO stranica = new StranicaDTO();

        stranica.setId(id);
        stranica.setSadrzaj(sadrzaj);
        stranica.setUdzbenik(udzbenik.toDto());
        stranica.setBrojStranice(brojStranice);

        return stranica;
    }
}
