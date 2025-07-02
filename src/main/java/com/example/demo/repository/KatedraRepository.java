package com.example.demo.repository;

import com.example.demo.model.Katedra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KatedraRepository extends CrudRepository<Katedra, Long> {

    List<Katedra> findByObrisanoFalse();
    List<Katedra> findByObrisanoTrue();
}