package com.example.demo.repository;

import com.example.demo.model.Silabus;
import com.example.demo.model.Stranica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StranicaRepository extends CrudRepository<Stranica, Long> {

    List<Stranica> findByObrisanoFalse();
    List<Stranica> findByObrisanoTrue();
    List<Stranica> findByUdzbenikId(Long udzbenikId);
    List<Stranica> findByBrojStranice(Integer brojStranice);

}
