package com.example.demo.repository;

import com.example.demo.model.PravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PravoPristupaRepository extends CrudRepository<PravoPristupa, Long> {

    List<PravoPristupa> findByObrisanoFalse();
    List<PravoPristupa> findByObrisanoTrue();
}
