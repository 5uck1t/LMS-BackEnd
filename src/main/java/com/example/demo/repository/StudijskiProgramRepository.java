package com.example.demo.repository;

import com.example.demo.model.StudijskiProgram;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudijskiProgramRepository extends CrudRepository<StudijskiProgram, Long> {

    List<StudijskiProgram> findByObrisanoFalse();
    List<StudijskiProgram> findByObrisanoTrue();

    List<StudijskiProgram> findByKatedra_IdAndObrisanoFalse(Long katedraId);
}