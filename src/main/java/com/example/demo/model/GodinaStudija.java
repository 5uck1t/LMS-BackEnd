package com.example.demo.model;

import com.example.demo.dto.GodinaStudijaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;

import java.time.Year;
import java.util.Set;

@Entity
public class GodinaStudija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^\\d{4}/\\d{4}$")
    private String godina;

    @ManyToOne
    private StudijskiProgram studijskiProgram;

    @OneToMany(mappedBy = "godinaStudija")
    private Set<StudentNaGodini> studentiNaGodini;

    @OneToMany(mappedBy = "godinaStudija")
    private Set<RealizacijaPredmeta> realizacijePredmeta;

    @ColumnDefault("false")
    private Boolean obrisano;

    public GodinaStudija(String godina, Long id, Boolean obrisano, Set<RealizacijaPredmeta> realizacijePredmeta, Set<StudentNaGodini> studentiNaGodini, StudijskiProgram studijskiProgram) {
        this.godina = godina;
        this.id = id;
        this.obrisano = obrisano;
        this.realizacijePredmeta = realizacijePredmeta;
        this.studentiNaGodini = studentiNaGodini;
        this.studijskiProgram = studijskiProgram;
    }

    public GodinaStudija() {
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RealizacijaPredmeta> getRealizacijePredmeta() {
        return realizacijePredmeta;
    }

    public void setRealizacijePredmeta(Set<RealizacijaPredmeta> realizacijePredmeta) {
        this.realizacijePredmeta = realizacijePredmeta;
    }

    public Set<StudentNaGodini> getStudentiNaGodini() {
        return studentiNaGodini;
    }

    public void setStudentiNaGodini(Set<StudentNaGodini> studentiNaGodini) {
        this.studentiNaGodini = studentiNaGodini;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public Boolean getObrisano() {
        return obrisano;
    }

    public void setObrisano(Boolean obrisano) {
        this.obrisano = obrisano;
    }

    public GodinaStudijaDTO toDto() {
        return new GodinaStudijaDTO(this.id, this.godina, this.studijskiProgram.toDto(), null, null, this.obrisano);
    }
}
