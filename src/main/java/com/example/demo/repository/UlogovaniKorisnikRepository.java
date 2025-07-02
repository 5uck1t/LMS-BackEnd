package com.example.demo.repository;

import com.example.demo.model.UlogovaniKorisnik;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UlogovaniKorisnikRepository extends CrudRepository<UlogovaniKorisnik, Long> {

    List<UlogovaniKorisnik> findByObrisanoFalse();
    List<UlogovaniKorisnik> findByObrisanoTrue();

    Optional<UlogovaniKorisnik> findUlogovaniKorisnikByUsername(String username);

    Optional<UlogovaniKorisnik> findUlogovaniKorisnikByUsernameAndPassword(String username, String password);

}