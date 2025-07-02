package com.example.demo.model;

import com.example.demo.dto.ForumDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;

    @ManyToOne
    private Forum forum;

    @OneToMany(mappedBy = "forum")
    private Set<Forum> forumi;

    @OneToMany(mappedBy = "forum")
    private Set<Obavestenje> obavjestenja;

    @OneToMany(mappedBy = "forum")
    private Set<ForumHasKorisnik> forumHasKorinsik;

    @ColumnDefault("false")
    private Boolean obrisano;

    public Forum(Forum forum, Set<ForumHasKorisnik> forumHasKorinsik, Set<Forum> forumi, Long id, String naziv, Set<Obavestenje> obavjestenja, Boolean obrisano) {
        this.forum = forum;
        this.forumHasKorinsik = forumHasKorinsik;
        this.forumi = forumi;
        this.id = id;
        this.naziv = naziv;
        this.obavjestenja = obavjestenja;
        this.obrisano = obrisano;
    }

    public Forum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Set<Obavestenje> getObavjestenja() {
        return obavjestenja;
    }

    public void setObavjestenja(Set<Obavestenje> obavjestenja) {
        this.obavjestenja = obavjestenja;
    }

    public Set<ForumHasKorisnik> getForumHasKorinsik() {
        return forumHasKorinsik;
    }

    public void setForumHasKorinsik(Set<ForumHasKorisnik> forumHasKorinsik) {
        this.forumHasKorinsik = forumHasKorinsik;
    }

    public Set<Forum> getForumi() {
        return forumi;
    }

    public void setForumi(Set<Forum> forumi) {
        this.forumi = forumi;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public ForumDTO toDto() {
        return new ForumDTO(this.id, this.naziv, this.forum.toDto(), null, null, null, this.obrisano);
    }
}
