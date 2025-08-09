package com.example.demo.repository;

import com.example.demo.model.Nastavnik;
import com.example.demo.model.Osoba;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NastavnikRepository extends CrudRepository<Nastavnik, Long> {

    List<Nastavnik> findByObrisanoFalse();
    List<Nastavnik> findByObrisanoTrue();
    
    Optional<Nastavnik> findByOsoba(Osoba osoba);

    Optional<Nastavnik> findByOsoba_Id(Long osobaId);
    
}