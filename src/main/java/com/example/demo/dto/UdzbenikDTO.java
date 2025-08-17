package com.example.demo.dto;

import com.example.demo.model.Udzbenik;
import com.example.demo.saveDto.UdzbenikSaveDTO;

import java.util.Set;

public class UdzbenikDTO {

    private Long id;
    private String naziv;
    private String autor;
    private String isbn;
    private Integer kolicina;
    private Set<IzdavanjeDTO> izdavanja;
    private Set<DopunjavanjeDTO> dopunjavanja;
    private Boolean obrisano = false;

    public UdzbenikDTO() {}

    public UdzbenikDTO(Long id, String naziv, String autor, String isbn, Integer kolicina,
                       Set<IzdavanjeDTO> izdavanja, Set<DopunjavanjeDTO> dopunjavanja, Boolean obrisano) {
        this.id = id;
        this.naziv = naziv;
        this.autor = autor;
        this.isbn = isbn;
        this.kolicina = kolicina;
        this.izdavanja = izdavanja;
        this.dopunjavanja = dopunjavanja;
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
    public Set<IzdavanjeDTO> getIzdavanja() { return izdavanja; }
    public void setIzdavanja(Set<IzdavanjeDTO> izdavanja) { this.izdavanja = izdavanja; }
    public Set<DopunjavanjeDTO> getDopunjavanja() { return dopunjavanja; }
    public void setDopunjavanja(Set<DopunjavanjeDTO> dopunjavanja) { this.dopunjavanja = dopunjavanja; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public Udzbenik toEntity() {
        Udzbenik e = new Udzbenik();
        e.setId(id);
        e.setNaziv(naziv);
        e.setAutor(autor);
        e.setIsbn(isbn);
        e.setKolicina(kolicina);
        e.setIzdavanja(null);
        e.setDopunjavanja(null);
        e.setObrisano(obrisano);
        return e;
    }

    public UdzbenikSaveDTO toSaveDto() {
        UdzbenikSaveDTO e = new UdzbenikSaveDTO();
        e.setId(id);
        e.setNaziv(naziv);
        e.setAutor(autor);
        e.setIsbn(isbn);
        e.setKolicina(kolicina);
        e.setObrisano(obrisano);
        return e;
    }
}
