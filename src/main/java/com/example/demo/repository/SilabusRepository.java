package com.example.demo.repository;

import com.example.demo.model.Predmet;
import com.example.demo.model.Silabus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SilabusRepository extends CrudRepository<Silabus, Long> {

    List<Silabus> findByObrisanoFalse();
    List<Silabus> findByObrisanoTrue();
    List<Silabus> findByPredmetInAndObrisanoFalse(List<Predmet> predmeti);
    Optional<Silabus> findByPredmetIdAndObrisanoFalse(Long predmetId);
    List<Silabus> findByPredmetId(Long predmetId);


}