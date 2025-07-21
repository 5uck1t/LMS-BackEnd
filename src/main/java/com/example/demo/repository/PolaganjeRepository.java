package com.example.demo.repository;

import com.example.demo.model.Polaganje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolaganjeRepository extends CrudRepository<Polaganje, Long> {

    List<Polaganje> findByObrisanoFalse();
    List<Polaganje> findByObrisanoTrue();


}
