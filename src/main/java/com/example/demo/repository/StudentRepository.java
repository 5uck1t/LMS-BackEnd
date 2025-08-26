package com.example.demo.repository;

import com.example.demo.model.Osoba;
import com.example.demo.model.Student;
import com.example.demo.model.StudentNaGodini;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findByObrisanoFalse();
    List<Student> findByObrisanoTrue();
    
    @Query("""
    	    SELECT s.id
    	    FROM Student s
    	    JOIN s.osoba o
    	    JOIN UlogovaniKorisnik uk ON uk.osoba.id = o.id
    	    WHERE uk.id = :userId
    	""")
    	Optional<Long> findStudentIdByUserId(@Param("userId") Long userId);

    
    Optional<Student> findByOsoba(Osoba osoba);
    
    @Query("SELECT DISTINCT s FROM Student s " +
            "JOIN s.osoba o " +
            "LEFT JOIN s.studentNaGodini sn " +
            "WHERE s.id NOT IN ( " +
            "   SELECT fks.ulogovaniKorisnik.id FROM ForumHasKorisnik fks " +
            "   WHERE fks.forum.id = :forumId AND fks.obrisano = false" +
            ") " +
            "AND (LOWER(o.ime) LIKE LOWER(CONCAT('%', :filter, '%')) " +
            "     OR LOWER(o.prezime) LIKE LOWER(CONCAT('%', :filter, '%')) " +
            "     OR CAST(sn.brojIndeksa AS string) LIKE CONCAT('%', :filter, '%') " +
            "     OR o.jmbg LIKE CONCAT('%', :filter, '%'))")
     List<Student> searchStudentsNotInForum(@Param("forumId") Long forumId, @Param("filter") String filter);

     @Query("SELECT DISTINCT s FROM Student s " +
            "JOIN s.osoba o " +
            "LEFT JOIN s.studentNaGodini sn " +
            "WHERE s.id NOT IN ( " +
            "   SELECT fks.ulogovaniKorisnik.id FROM ForumHasKorisnik fks " +
            "   WHERE fks.forum.id = :forumId AND fks.obrisano = false" +
            ")")
     List<Student> findStudentsNotInForum(@Param("forumId") Long forumId);
}