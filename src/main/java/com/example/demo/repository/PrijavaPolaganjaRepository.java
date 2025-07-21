package com.example.demo.repository;

import com.example.demo.model.Polaganje;
import com.example.demo.model.PrijavaPolaganja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaPolaganjaRepository extends CrudRepository<PrijavaPolaganja, Long> {

    List<PrijavaPolaganja> findByObrisanoFalse();
    List<PrijavaPolaganja> findByObrisanoTrue();

    List<PrijavaPolaganja> findByPolaganje_Id(Long polaganjeId);

    List<PrijavaPolaganja> findByPohadjanjePredmeta_Id(Long pohadjanjePredmetaId);
}
