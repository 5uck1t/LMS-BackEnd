package com.example.demo.repository;

import com.example.demo.model.Forum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends CrudRepository<Forum, Long> {

    List<Forum> findByObrisanoFalse();
    List<Forum> findByObrisanoTrue();
}