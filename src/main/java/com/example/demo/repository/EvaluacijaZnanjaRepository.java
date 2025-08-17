package com.example.demo.repository;

import com.example.demo.model.EvaluacijaZnanja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacijaZnanjaRepository extends CrudRepository<EvaluacijaZnanja, Long> {

    List<EvaluacijaZnanja> findByObrisanoFalse();
    List<EvaluacijaZnanja> findByObrisanoTrue();
    
    List<EvaluacijaZnanja> findByRealizacijaPredmeta_Predmet_IdAndObrisanoFalse(Long predmetId);

    List<EvaluacijaZnanja> findByRealizacijaPredmeta_Id(Long realizacijaPredmetaId);
}