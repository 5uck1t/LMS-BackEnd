package com.example.demo.repository;

import com.example.demo.model.Predmet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends CrudRepository<Predmet, Long> {

    List<Predmet> findByObrisanoFalse();
    List<Predmet> findByObrisanoTrue();
}