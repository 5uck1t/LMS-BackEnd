package com.example.demo.repository;

import com.example.demo.model.NaucnaOblast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaucnaOblastRepository extends CrudRepository<NaucnaOblast, Long> {

    List<NaucnaOblast> findByObrisanoFalse();
    List<NaucnaOblast> findByObrisanoTrue();
}