package com.example.demo.repository;

import com.example.demo.model.StudentNaGodini;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentNaGodiniRepository extends CrudRepository<StudentNaGodini, Long> {

    List<StudentNaGodini> findByObrisanoFalse();
    List<StudentNaGodini> findByObrisanoTrue();
    
    
    @Query("""
    	    SELECT DISTINCT s FROM StudentNaGodini s
    	    WHERE (:ime IS NULL OR LOWER(s.student.osoba.ime) LIKE LOWER(CONCAT('%', :ime, '%')))
    	      AND (:prezime IS NULL OR LOWER(s.student.osoba.prezime) LIKE LOWER(CONCAT('%', :prezime, '%')))
    	      AND (:brojIndeksa IS NULL OR s.brojIndeksa = :brojIndeksa)
    	      AND (:godinaUpisa IS NULL OR FUNCTION('YEAR', s.datumUpisa) = :godinaUpisa)
    	      
    	""")
    	List<StudentNaGodini> searchStudents(
    	    @Param("ime") String ime,
    	    @Param("prezime") String prezime,
    	    @Param("brojIndeksa") Long brojIndeksa,
    	    @Param("godinaUpisa") Integer godinaUpisa
    	);
    
    
    @Query("SELECT s FROM StudentNaGodini s WHERE " +
    	       "LOWER(s.student.osoba.ime) LIKE %:query% OR " +
    	       "LOWER(s.student.osoba.prezime) LIKE %:query% OR " +
    	       "CAST(s.brojIndeksa AS string) LIKE %:query% OR " +
    	       "CAST(YEAR(s.datumUpisa) AS string) LIKE %:query%")
    	List<StudentNaGodini> searchStudentsByQuery(@Param("query") String query);


}