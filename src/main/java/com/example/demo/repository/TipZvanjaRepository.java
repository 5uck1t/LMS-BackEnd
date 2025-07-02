package com.example.demo.repository;

import com.example.demo.model.TipZvanja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipZvanjaRepository extends CrudRepository<TipZvanja, Long> {

    List<TipZvanja> findByObrisanoFalse();
    List<TipZvanja> findByObrisanoTrue();
}