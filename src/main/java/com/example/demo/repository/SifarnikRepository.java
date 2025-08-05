package com.example.demo.repository;

import com.example.demo.model.Sifarnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SifarnikRepository extends CrudRepository<Sifarnik, Long> {

    List<Sifarnik> findByObrisanoFalse();
    List<Sifarnik> findByObrisanoTrue();
}
