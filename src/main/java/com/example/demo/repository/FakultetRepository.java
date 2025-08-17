package com.example.demo.repository;

import com.example.demo.model.Fakultet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakultetRepository extends CrudRepository<Fakultet, Long> {

    List<Fakultet> findByObrisanoFalse();
    List<Fakultet> findByObrisanoTrue();
}