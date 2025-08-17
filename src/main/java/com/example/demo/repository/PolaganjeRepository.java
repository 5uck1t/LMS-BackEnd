package com.example.demo.repository;

import com.example.demo.model.Polaganje;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolaganjeRepository extends CrudRepository<Polaganje, Long> {

    List<Polaganje> findByObrisanoFalse();
    List<Polaganje> findByObrisanoTrue();

    List<Polaganje> findByEvaluacijaZnanja_RealizacijaPredmeta_Predmet_IdInAndObrisanoFalse(List<Long> predmetIds);


    @Query("""
    	    SELECT p FROM Polaganje p 
    	    WHERE p.evaluacijaZnanja.realizacijaPredmeta.predmet.id IN :predmetIds 
    	    AND p.rok.naziv IN :dozvoljeniRokovi 
    	    AND p.rok.kraj > :trenutnoVreme
    	""")
    	List<Polaganje> findDostupnaZaPredmete(
    	    @Param("predmetIds") List<Long> predmetIds,
    	    @Param("dozvoljeniRokovi") Set<String> dozvoljeniRokovi,
    	    @Param("trenutnoVreme") LocalDateTime trenutnoVreme
    	);


    List<Polaganje> findByEvaluacijaZnanja_IdAndObrisanoFalse(Long evaluacijaId);

}
