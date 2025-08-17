package com.example.demo.repository;

import com.example.demo.model.Dopunjavanje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DopunjavanjeRepository extends CrudRepository<Dopunjavanje, Long> {

    List<Dopunjavanje> findByObrisanoFalse();
    List<Dopunjavanje> findByObrisanoTrue();

    List<Dopunjavanje> findByUdzbenikId(Long udzbenikId);
}
