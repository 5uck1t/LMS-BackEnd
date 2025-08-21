package com.example.demo.dto;

import java.util.List;
import java.util.Map;

public class IzvestajPolaganjaDTO {
    private Long rokId;
    private Long predmetId;
    private String rokNaziv;
    private String predmetNaziv;

    // histogram: ključ je ocena (5..10), vrednost je broj
    private Map<Integer, Long> histogram;

    // agregati
    private Long brojPrijava;
    private Double prosekBodova;
    private Double minBodovi;
    private Double maxBodovi;

    private List<IzvestajPolaganjaRowDTO> rezultati;

    public Long getRokId() { return rokId; }
    public void setRokId(Long rokId) { this.rokId = rokId; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

    public String getRokNaziv() { return rokNaziv; }
    public void setRokNaziv(String rokNaziv) { this.rokNaziv = rokNaziv; }

    public String getPredmetNaziv() { return predmetNaziv; }
    public void setPredmetNaziv(String predmetNaziv) { this.predmetNaziv = predmetNaziv; }

    public Map<Integer, Long> getHistogram() { return histogram; }
    public void setHistogram(Map<Integer, Long> histogram) { this.histogram = histogram; }

    public Long getBrojPrijava() { return brojPrijava; }
    public void setBrojPrijava(Long brojPrijava) { this.brojPrijava = brojPrijava; }

    public Double getProsekBodova() { return prosekBodova; }
    public void setProsekBodova(Double prosekBodova) { this.prosekBodova = prosekBodova; }

    public Double getMinBodovi() { return minBodovi; }
    public void setMinBodovi(Double minBodovi) { this.minBodovi = minBodovi; }

    public Double getMaxBodovi() { return maxBodovi; }
    public void setMaxBodovi(Double maxBodovi) { this.maxBodovi = maxBodovi; }

    public List<IzvestajPolaganjaRowDTO> getRezultati() { return rezultati; }
    public void setRezultati(List<IzvestajPolaganjaRowDTO> rezultati) { this.rezultati = rezultati; }
}
