package com.example.demo.repository;

import com.example.demo.model.Izdavanje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IzdavanjeRepository extends CrudRepository<Izdavanje, Long> {

    List<Izdavanje> findByObrisanoFalse();
    List<Izdavanje> findByObrisanoTrue();

    List<Izdavanje> findByUdzbenikId(Long udzbenikId);
    List<Izdavanje> findByStudentId(Long studentId);
}
