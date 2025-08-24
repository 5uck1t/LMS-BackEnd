package com.example.demo.repository;

import com.example.demo.model.PohadjanjePredmeta;
import com.example.demo.model.Predmet;
import com.example.demo.model.Student;
import com.example.demo.model.StudentNaGodini;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PohadjanjePredmetaRepository extends CrudRepository<PohadjanjePredmeta, Long> {

    List<PohadjanjePredmeta> findByObrisanoFalse();
    List<PohadjanjePredmeta> findByObrisanoTrue();
    
    List<PohadjanjePredmeta> findByStudentNaGodiniStudentIdAndObrisanoFalse(Long studentId);
    
    

    @Query("SELECT pp.realizacijaPredmeta.predmet FROM PohadjanjePredmeta pp " +
            "WHERE pp.studentNaGodini.student.id = :studentId AND pp.obrisano = false")
    List<Predmet> findPredmetiByStudentId(@Param("studentId") Long studentId);

    List<PohadjanjePredmeta> findByStudentNaGodini_Id(Long studentNaGodiniId);

    @Query("SELECT pp.realizacijaPredmeta.predmet FROM PohadjanjePredmeta pp " +
            "WHERE pp.studentNaGodini.student.id = :studentId " +
            "AND pp.konacnaOcena > 5")
    List<Predmet> findPredmetiByStudentIdAndKonacnaOcenaNotNull(@Param("studentId") Long studentId);

    @Query("SELECT pp.realizacijaPredmeta.predmet FROM PohadjanjePredmeta pp " +
            "WHERE pp.studentNaGodini.student.id = :studentId " +
            "AND pp.konacnaOcena < 6")
    List<Predmet> findPredmetiByStudentIdAndKonacnaOcenaIsNull(@Param("studentId") Long studentId);

    @Query("SELECT pp.studentNaGodini FROM PohadjanjePredmeta pp WHERE pp.realizacijaPredmeta.predmet.id = :predmetId")
    List<StudentNaGodini> findStudentsByPredmetId(@Param("predmetId") Long predmetId);
    
    List<PohadjanjePredmeta> findByStudentNaGodini_Student_IdAndObrisanoFalse(Long studentId);

}