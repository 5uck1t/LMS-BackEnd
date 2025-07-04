package com.example.demo.repository;

import com.example.demo.model.Osoba;
import com.example.demo.model.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByObrisanoFalse();
    List<Student> findByObrisanoTrue();

    
    Optional<Student> findByOsoba(Osoba osoba);
}