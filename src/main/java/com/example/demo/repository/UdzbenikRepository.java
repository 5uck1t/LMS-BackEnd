package com.example.demo.repository;

import com.example.demo.model.Udzbenik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UdzbenikRepository extends CrudRepository<Udzbenik, Long> {

    List<Udzbenik> findByObrisanoFalse();
    List<Udzbenik> findByObrisanoTrue();
}
