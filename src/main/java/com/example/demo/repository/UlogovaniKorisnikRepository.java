package com.example.demo.repository;

import com.example.demo.model.UlogovaniKorisnik;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UlogovaniKorisnikRepository extends CrudRepository<UlogovaniKorisnik, Long> {

    List<UlogovaniKorisnik> findByObrisanoFalse();
    List<UlogovaniKorisnik> findByObrisanoTrue();

    boolean existsByUsername(String username);

    Optional<UlogovaniKorisnik> findUlogovaniKorisnikByUsername(String username);

    Optional<UlogovaniKorisnik> findUlogovaniKorisnikByUsernameAndPassword(String username, String password);

    Optional<UlogovaniKorisnik> findUlogovaniKorisnikByOsoba_Id(Long osobaId);
    
    @Query("SELECT u FROM UlogovaniKorisnik u LEFT JOIN FETCH u.dodeljenaPravaPristupa d LEFT JOIN FETCH d.pravoPristupa WHERE u.username = :username")
    Optional<UlogovaniKorisnik> findByUsernameWithRoles(@Param("username") String username);


}