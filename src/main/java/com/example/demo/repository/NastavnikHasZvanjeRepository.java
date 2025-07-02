package com.example.demo.repository;

import com.example.demo.model.NastavnikHasZvanje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NastavnikHasZvanjeRepository extends CrudRepository<NastavnikHasZvanje, Long> {

    List<NastavnikHasZvanje> findByObrisanoFalse();
    List<NastavnikHasZvanje> findByObrisanoTrue();
}