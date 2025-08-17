package com.example.demo.repository;

import com.example.demo.model.DatumPredmeta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DatumPredmetaRepository extends CrudRepository<DatumPredmeta, Long> {

    List<DatumPredmeta> findByObrisanoFalse();
    List<DatumPredmeta> findByObrisanoTrue();

    @Query("SELECT d FROM DatumPredmeta d WHERE d.rok.id = :rokId AND d.realizacijaPredmeta.predmet.id = :predmetId AND d.obrisano = false")
    Optional<DatumPredmeta> findOneByRokIdAndPredmetId(@Param("rokId") Long rokId, @Param("predmetId") Long predmetId);

    @Query("SELECT d FROM DatumPredmeta d WHERE d.rok.id = :rokId AND d.obrisano = false")
    List<DatumPredmeta> findByRokId(@Param("rokId") Long rokId);

}
