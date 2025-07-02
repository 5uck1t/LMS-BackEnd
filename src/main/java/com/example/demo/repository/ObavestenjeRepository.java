package com.example.demo.repository;

import com.example.demo.model.Obavestenje;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObavestenjeRepository extends CrudRepository<Obavestenje, Long> {

    List<Obavestenje> findByObrisanoFalse();
    List<Obavestenje> findByObrisanoTrue();

    List<Obavestenje> findByForum_Id(Long forumId);
}