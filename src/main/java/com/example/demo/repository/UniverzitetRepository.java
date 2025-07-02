package com.example.demo.repository;

import com.example.demo.model.Univerzitet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniverzitetRepository extends CrudRepository<Univerzitet, Long> {

    List<Univerzitet> findByObrisanoFalse();
    List<Univerzitet> findByObrisanoTrue();
}