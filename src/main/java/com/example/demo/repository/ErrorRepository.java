package com.example.demo.repository;

import com.example.demo.model.Drzava;
import com.example.demo.model.ErrorLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ErrorRepository extends CrudRepository<ErrorLog, Long> {

    List<ErrorLog> findByObrisanoFalse();
    List<ErrorLog> findByObrisanoTrue();
}
