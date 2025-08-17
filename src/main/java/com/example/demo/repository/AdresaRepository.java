package com.example.demo.repository;

import com.example.demo.model.Adresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdresaRepository extends CrudRepository<Adresa, Long> {

    List<Adresa> findByObrisanoFalse();
    List<Adresa> findByObrisanoTrue();
}