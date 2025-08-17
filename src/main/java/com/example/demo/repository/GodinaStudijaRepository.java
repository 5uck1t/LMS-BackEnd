package com.example.demo.repository;

import com.example.demo.model.GodinaStudija;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GodinaStudijaRepository extends CrudRepository<GodinaStudija, Long> {

    List<GodinaStudija> findByObrisanoFalse();
    List<GodinaStudija> findByObrisanoTrue();


}