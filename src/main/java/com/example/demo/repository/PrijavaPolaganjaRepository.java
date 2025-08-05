package com.example.demo.repository;

import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Polaganje;
import com.example.demo.model.PrijavaPolaganja;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import com.example.demo.model.Polaganje;
import com.example.demo.model.PrijavaPolaganja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaPolaganjaRepository extends CrudRepository<PrijavaPolaganja, Long> {

    List<PrijavaPolaganja> findByObrisanoFalse();
    List<PrijavaPolaganja> findByObrisanoTrue();

    List<PrijavaPolaganja> findByPolaganje_Id(Long polaganjeId);

    List<PrijavaPolaganja> findByPohadjanjePredmeta_Id(Long pohadjanjePredmetaId);
    
    List<PrijavaPolaganja> findByPohadjanjePredmetaStudentNaGodiniStudentId(Long studentId);

    boolean existsByPohadjanjePredmetaAndPolaganje(PohadjanjePredmeta pohadjanjePredmeta, Polaganje polaganje);
    
    List<PrijavaPolaganja> findByPohadjanjePredmeta_StudentNaGodini_Student_Id(Long studentId);
    
    @Query("""
    	    SELECT p FROM PrijavaPolaganja p
    	    WHERE p.pohadjanjePredmeta.studentNaGodini.student.id = :studentId
    	""")
    	List<PrijavaPolaganja> findByStudentId(@Param("studentId") Long studentId);

}
