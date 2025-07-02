package com.example.demo.repository;

import com.example.demo.model.Mesto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MestoRepository extends CrudRepository<Mesto, Long> {

    List<Mesto> findByObrisanoFalse();
    List<Mesto> findByObrisanoTrue();
}