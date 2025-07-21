package com.example.demo.repository;

import com.example.demo.model.Obavestenje;
import com.example.demo.model.Odgovor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdgovorRepository extends CrudRepository<Odgovor, Long> {

    List<Odgovor> findByObrisanoFalse();
    List<Odgovor> findByObrisanoTrue();

    List<Odgovor> findByZadatak_Id(Long zadatakId);
}
