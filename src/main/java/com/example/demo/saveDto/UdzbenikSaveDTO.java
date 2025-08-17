package com.example.demo.saveDto;

import com.example.demo.model.Udzbenik;

public class UdzbenikSaveDTO {

    private Long id;
    private String naziv;
    private String autor;
    private String isbn;
    private Integer kolicina;
    private Boolean obrisano = false;

    public UdzbenikSaveDTO() {}

    public UdzbenikSaveDTO(Long id, String naziv, String autor, String isbn, Integer kolicina, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.isbn = isbn;
        this.kolicina = kolicina;
        this.obrisano = obrisano;
    }

    // getters/setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Integer getKolicina() { return kolicina; }
    public void setKolicina(Integer kolicina) { this.kolicina = kolicina; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Udzbenik toEntity() {
        Udzbenik e = new Udzbenik();
        e.setId(id);
        e.setNaziv(naziv);
        e.setAutor(autor);
        e.setIsbn(isbn);
        e.setKolicina(kolicina);
        e.setObrisano(obrisano);
        return e;
    }
}
