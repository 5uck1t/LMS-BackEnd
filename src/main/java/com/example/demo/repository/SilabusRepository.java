package com.example.demo.repository;

import com.example.demo.model.Silabus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SilabusRepository extends CrudRepository<Silabus, Long> {

    List<Silabus> findByObrisanoFalse();
    List<Silabus> findByObrisanoTrue();
}