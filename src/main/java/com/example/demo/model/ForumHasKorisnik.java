package com.example.demo.model;

import com.example.demo.dto.ForumHasKorisnikDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class ForumHasKorisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UlogovaniKorisnik ulogovaniKorisnik;

    @ManyToOne
    private Forum forum;

    @ColumnDefault("false")
    private Boolean obrisano;

    public ForumHasKorisnik(Forum forum, Long id, Boolean obrisano, UlogovaniKorisnik ulogovaniKorisnik) {
        this.forum = forum;
        this.id = id;
        this.obrisano = obrisano;
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }

    public ForumHasKorisnik() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public UlogovaniKorisnik getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }
    public void setUlogovaniKorisnik(UlogovaniKorisnik ulogovaniKorisnik) {
        this.ulogovaniKorisnik = ulogovaniKorisnik;
    }
    public Forum getForum() {
        return forum;
    }
    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public ForumHasKorisnikDTO toDto() {
        return new ForumHasKorisnikDTO(this.id, this.ulogovaniKorisnik.toDto(), this.forum.toDto(), this.obrisano);
    }
}
