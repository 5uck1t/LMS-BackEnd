package com.example.demo.service;

import com.example.demo.dto.StudentDTO;
import com.example.demo.dto.StudenttDTO;
import com.example.demo.dto.UlogovaniKorisnikDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Osoba;
import com.example.demo.model.Student;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.repository.StudentRepository;
import com.example.demo.saveDto.StudentSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UlogovaniKorisnikService ulogovaniKorisnikService;

    @Autowired
    private OsobaService osobaService;

    public List<StudentDTO> findAll() {
        return ((List<Student>) studentRepository.findAll())
                .stream()
                .map(Student::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findAllActive() {
        return ((List<Student>) studentRepository.findByObrisanoFalse())
                .stream()
                .map(Student::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> findAllDeleted() {
        return ((List<Student>) studentRepository.findByObrisanoTrue())
                .stream()
                .map(Student::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentDTO> findById(Long id) {
        return studentRepository.findById(id).map(Student::toDto);
    }

    public Optional<Student> findEntityById(Long id) {
        return studentRepository.findById(id);
    }

    public StudentDTO save(StudentSaveDTO student) {

        Student novi = student.toEntity();

        novi.setOsoba(osobaService.findEntityById(student.getOsoba_id())
                .orElseThrow(() -> new ResourceNotFoundException("Osoba with id:" + student.getOsoba_id() + " not found")));

        return studentRepository.save(novi).toDto();
    }

    public void delete(StudentDTO student) {
        student.setObrisano(true);
        studentRepository.save(student.toEntity());
    }

    public void delete(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student student = optional.get();
            student.setObrisano(true);
            studentRepository.save(student);
        }
    }

    public void vrati(StudentDTO student) {
        student.setObrisano(false);
        studentRepository.save(student.toEntity());
    }

    public void vrati(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student student = optional.get();
            student.setObrisano(false);
            studentRepository.save(student);
        }
    }
    
    public Optional<Long> findStudentIdByUserId(Long userId) {
        return studentRepository.findStudentIdByUserId(userId);
    }
    

    
    public Optional<Student> findByOsoba(Osoba osoba) {
        return studentRepository.findByOsoba(osoba);
    }
    
    
    public List<StudenttDTO> getAvailableStudents(Long forumId, String filter) {
        List<Student> studenti;
        studenti = studentRepository.findStudentsNotInForum(forumId);


        return studenti.stream().map(s -> {
            UlogovaniKorisnikDTO ulogovaniKorisnik = ulogovaniKorisnikService.findByOsobaId(s.getOsoba().getId()).orElse(null);

            if (ulogovaniKorisnik != null) {
                Long ukId = ulogovaniKorisnik.getId();


                Long brojIndeksa = null;
                if (s.getStudentNaGodini() != null && !s.getStudentNaGodini().isEmpty()) {
                    // npr. uzimamo poslednji
                    brojIndeksa = s.getStudentNaGodini().iterator().next().getBrojIndeksa();
                }
                return new StudenttDTO(
                    s.getId(),
                    s.getOsoba().getIme(),
                    s.getOsoba().getPrezime(),
                    s.getOsoba().getJmbg(),
                    brojIndeksa,
                        ukId
                );
            }
            return null;

        }).toList();
    }
}