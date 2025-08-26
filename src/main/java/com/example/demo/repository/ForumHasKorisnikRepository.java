package com.example.demo.repository;

import com.example.demo.model.Forum;
import com.example.demo.model.ForumHasKorisnik;
import com.example.demo.model.UlogovaniKorisnik;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ForumHasKorisnikRepository extends CrudRepository<ForumHasKorisnik, Long> {

    List<ForumHasKorisnik> findByObrisanoFalse();
    List<ForumHasKorisnik> findByObrisanoTrue();

    @Query("SELECT fhk.forum FROM ForumHasKorisnik fhk WHERE fhk.ulogovaniKorisnik.id = :userId")
    List<Forum> findActiveForumsByUserId(@Param("userId") Long userId);
    
    @Query("SELECT fhk.ulogovaniKorisnik FROM ForumHasKorisnik fhk WHERE fhk.forum.id = :forumId AND fhk.obrisano = false")
    List<UlogovaniKorisnik> findKorisniciByForumId(@Param("forumId") Long forumId);
    
    Optional<ForumHasKorisnik> findByForumAndUlogovaniKorisnik(Forum forum, UlogovaniKorisnik ulogovaniKorisnik);
    
 // Dodaj ovu funkciju za uklanjanje po ID-jevima
    List<ForumHasKorisnik> findByForumIdAndUlogovaniKorisnikId(Long forumId, Long ulogovaniKorisnikId);

    
    

}