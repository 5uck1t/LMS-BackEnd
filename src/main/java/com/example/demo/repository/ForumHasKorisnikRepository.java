package com.example.demo.repository;

import com.example.demo.model.Forum;
import com.example.demo.model.ForumHasKorisnik;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumHasKorisnikRepository extends CrudRepository<ForumHasKorisnik, Long> {

    List<ForumHasKorisnik> findByObrisanoFalse();
    List<ForumHasKorisnik> findByObrisanoTrue();

    @Query("SELECT fhk.forum FROM ForumHasKorisnik fhk WHERE fhk.ulogovaniKorisnik.id = :userId AND fhk.obrisano = false AND fhk.forum.obrisano = false")
    List<Forum> findActiveForumsByUserId(@Param("userId") Long userId);
}