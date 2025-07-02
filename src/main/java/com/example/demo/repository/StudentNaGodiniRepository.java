package com.example.demo.repository;

import com.example.demo.model.StudentNaGodini;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentNaGodiniRepository extends CrudRepository<StudentNaGodini, Long> {

    List<StudentNaGodini> findByObrisanoFalse();
    List<StudentNaGodini> findByObrisanoTrue();
}