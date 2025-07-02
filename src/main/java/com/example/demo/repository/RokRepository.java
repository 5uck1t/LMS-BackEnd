package com.example.demo.repository;

import com.example.demo.model.Rok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RokRepository extends CrudRepository<Rok, Long> {

    List<Rok> findByObrisanoFalse();
    List<Rok> findByObrisanoTrue();
}