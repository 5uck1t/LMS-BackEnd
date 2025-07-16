package com.example.demo.repository;

import com.example.demo.model.Termin;
import com.example.demo.model.TipZvanja;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TerminRepository extends CrudRepository<Termin, Long> {

    List<Termin> findByObrisanoFalse();
    List<Termin> findByObrisanoTrue();

    List<Termin> findByRealizacijaPredmeta_Id(Long realizacijaPredmetaId);
}
