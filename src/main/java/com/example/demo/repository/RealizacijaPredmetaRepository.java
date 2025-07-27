package com.example.demo.repository;

import com.example.demo.model.Predmet;
import com.example.demo.model.RealizacijaPredmeta;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealizacijaPredmetaRepository extends CrudRepository<RealizacijaPredmeta, Long> {

    List<RealizacijaPredmeta> findByObrisanoFalse();
    List<RealizacijaPredmeta> findByObrisanoTrue();

    @Query("SELECT rp.predmet FROM RealizacijaPredmeta rp WHERE rp.nastavnik.id = :nastavnikId AND rp.obrisano = false")
    List<Predmet> findPredmetiByNastavnikId(@Param("nastavnikId") Long nastavnikId);

    @Query("SELECT rp FROM RealizacijaPredmeta rp WHERE rp.godinaStudija.id = :godinaStudijaid AND rp.obrisano = false")
    List<RealizacijaPredmeta> findRealizacijaPredmetaByGodinaStudijaId(@Param("godinaStudijaid") Long godinaStudijaid);

}