package com.example.demo.repository;

import com.example.demo.model.Nastavnik;
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
    
    List<RealizacijaPredmeta> findByNastavnikIdAndObrisanoFalse(Long nastavnikId);
    
    List<RealizacijaPredmeta> findByNastavnik(Nastavnik nastavnik);
    
    List<RealizacijaPredmeta> findByPredmetId(Long predmetId);



    @Query("SELECT rp FROM RealizacijaPredmeta rp WHERE rp.godinaStudija.id = :godinaStudijaid AND rp.obrisano = false")
    List<RealizacijaPredmeta> findRealizacijaPredmetaByGodinaStudijaId(@Param("godinaStudijaid") Long godinaStudijaid);

    @Query("SELECT rp FROM RealizacijaPredmeta rp WHERE rp.godinaStudija.studijskiProgram.id = :studijskiProgramid AND rp.obrisano = false")
    List<RealizacijaPredmeta> findRealizacijaPredmetaByStudijskiProgramId(@Param("studijskiProgramid") Long studijskiProgramid);
    
    @Query("SELECT rp FROM RealizacijaPredmeta rp WHERE rp.id = :id AND rp.obrisano = false")
    RealizacijaPredmeta findActiveById(@Param("id") Long id);


}