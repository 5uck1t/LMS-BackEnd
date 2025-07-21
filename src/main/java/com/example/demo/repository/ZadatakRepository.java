package com.example.demo.repository;

import com.example.demo.model.Odgovor;
import com.example.demo.model.Zadatak;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZadatakRepository extends CrudRepository<Zadatak, Long> {

    List<Zadatak> findByObrisanoFalse();
    List<Zadatak> findByObrisanoTrue();

    List<Zadatak> findByEvaluacijaZnanja_Id(Long evaluacijaZnanjaId);
}
