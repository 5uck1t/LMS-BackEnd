package com.example.demo.repository;

import com.example.demo.model.Zvanje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZvanjeRepository extends CrudRepository<Zvanje, Long> {

    List<Zvanje> findByObrisanoFalse();
    List<Zvanje> findByObrisanoTrue();
}