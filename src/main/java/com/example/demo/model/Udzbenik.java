package com.example.demo.model;

import com.example.demo.dto.UdzbenikDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Udzbenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    private String autor;

    private String isbn;

    private Integer kolicina;

    @OneToMany(mappedBy = "udzbenik")
    private Set<Izdavanje> izdavanja;

    @OneToMany(mappedBy = "udzbenik")
    private Set<Dopunjavanje> dopunjavanja;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Udzbenik() {}

    public Udzbenik(Long id, String naziv, String autor, String isbn, Integer kolicina, Boolean obrisano) {
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
    public Set<Izdavanje> getIzdavanja() { return izdavanja; }
    public void setIzdavanja(Set<Izdavanje> izdavanja) { this.izdavanja = izdavanja; }
    public Set<Dopunjavanje> getDopunjavanja() { return dopunjavanja; }
    public void setDopunjavanja(Set<Dopunjavanje> dopunjavanja) { this.dopunjavanja = dopunjavanja; }
    public Boolean getObrisano() { return obrisano; }
    public void setObrisano(Boolean obrisano) { this.obrisano = obrisano; }

    public UdzbenikDTO toDto() {
        return new UdzbenikDTO(
                this.id,
                this.naziv,
                this.autor,
                this.isbn,
                this.kolicina,
                null, // izdavanja
                null, // dopunjavanja
                this.obrisano
        );
    }
}
