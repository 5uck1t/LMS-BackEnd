package com.example.demo.repository;

import com.example.demo.model.Nastavnik;
import com.example.demo.model.Osoba;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NastavnikRepository extends CrudRepository<Nastavnik, Long> {

    List<Nastavnik> findByObrisanoFalse();
    List<Nastavnik> findByObrisanoTrue();
    
    Optional<Nastavnik> findByOsoba(Osoba osoba);

    Optional<Nastavnik> findByOsoba_Id(Long osobaId);
    
    @Query("""
    	    SELECT n.id
    	    FROM Nastavnik n
    	    JOIN n.osoba o
    	    JOIN UlogovaniKorisnik uk ON uk.osoba.id = o.id
    	    WHERE uk.id = :userId
    	""")
    	Optional<Long> findNastavnikIdByUserId(@Param("userId") Long userId);
    
    @Query("""
            SELECT uk.email 
            FROM RealizacijaPredmeta r
            JOIN r.nastavnik n
            JOIN n.osoba o
            JOIN UlogovaniKorisnik uk ON uk.osoba.id = o.id
            WHERE r.predmet.id = :predmetId
        """)
        List<String> findEmailsByPredmetId(@Param("predmetId") Long predmetId);
    
    
    @Query("""
    	    SELECT n FROM Nastavnik n
    	    WHERE n.id NOT IN (
    	        SELECT fhk.ulogovaniKorisnik.osoba.nastavnik.id FROM ForumHasKorisnik fhk
    	        WHERE fhk.forum.id = :forumId
    	    )
    	""")
    	List<Nastavnik> findNastavniciNotInForum(@Param("forumId") Long forumId);


    @Query("""
    	    SELECT n FROM Nastavnik n
    	    WHERE n.id NOT IN (
    	        SELECT fhk.ulogovaniKorisnik.osoba.nastavnik.id FROM ForumHasKorisnik fhk
    	        WHERE fhk.forum.id = :forumId
    	    )
    	    AND (LOWER(n.osoba.ime) LIKE LOWER(CONCAT('%', :filter, '%')) 
    	         OR LOWER(n.osoba.prezime) LIKE LOWER(CONCAT('%', :filter, '%')))
    	""")
    	List<Nastavnik> searchNastavniciNotInForum(@Param("forumId") Long forumId,
    	                                           @Param("filter") String filter);

}