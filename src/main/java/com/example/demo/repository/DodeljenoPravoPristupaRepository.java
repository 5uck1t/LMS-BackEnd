package com.example.demo.repository;

import com.example.demo.model.DodeljenoPravoPristupa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DodeljenoPravoPristupaRepository extends CrudRepository<DodeljenoPravoPristupa, Long> {

    List<DodeljenoPravoPristupa> findByObrisanoFalse();
    List<DodeljenoPravoPristupa> findByObrisanoTrue();

    List<DodeljenoPravoPristupa> findDodeljenoPravoPristupaByUlogovaniKorisnik_Username(String ulogovaniKorisnikUsername);

    List<DodeljenoPravoPristupa> findDodeljenoPravoPristupaByUlogovaniKorisnik_UsernameAndObrisanoFalse(String ulogovaniKorisnikUsername);

    List<DodeljenoPravoPristupa> findDodeljenoPravoPristupaByUlogovaniKorisnik_IdAndObrisanoFalse(Long ulogovaniKorisnikId);
}