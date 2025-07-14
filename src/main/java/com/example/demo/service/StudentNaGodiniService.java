package com.example.demo.service;

import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.dto.StudentSearchDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Silabus;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.repository.StudentNaGodiniRepository;
import com.example.demo.saveDto.StudentNaGodiniSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentNaGodiniService {

    @Autowired
    private StudentNaGodiniRepository studentNaGodiniRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GodinaStudijaService godinaStudijaService;

    public List<StudentNaGodiniDTO> findAll() {
        return ((List<StudentNaGodini>) studentNaGodiniRepository.findAll())
                .stream()
                .map(StudentNaGodini::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentNaGodiniDTO> findAllActive() {
        return ((List<StudentNaGodini>) studentNaGodiniRepository.findByObrisanoFalse())
                .stream()
                .map(StudentNaGodini::toDto)
                .collect(Collectors.toList());
    }

    public List<StudentNaGodiniDTO> findAllDeleted() {
        return ((List<StudentNaGodini>) studentNaGodiniRepository.findByObrisanoTrue())
                .stream()
                .map(StudentNaGodini::toDto)
                .collect(Collectors.toList());
    }

    public Optional<StudentNaGodiniDTO> findById(Long id) {
        return studentNaGodiniRepository.findById(id).map(StudentNaGodini::toDto);
    }

    public Optional<StudentNaGodini> findEntityById(Long id) {
        return studentNaGodiniRepository.findById(id);
    }

    public StudentNaGodiniDTO save(StudentNaGodiniSaveDTO studentNaGodini) {

        StudentNaGodini novi = studentNaGodini.toEntity();

        novi.setStudent(studentService.findEntityById(studentNaGodini.getStudent_id())
                .orElseThrow(() -> new ResourceNotFoundException("Student with id:" + studentNaGodini.getStudent_id() + " not found")));

        novi.setGodinaStudija(godinaStudijaService.findEntityById(studentNaGodini.getGodinaStudija_id())
                .orElseThrow(() -> new ResourceNotFoundException("Godina studija with id:" + studentNaGodini.getGodinaStudija_id() + " not found")));

        return studentNaGodiniRepository.save(novi).toDto();
    }

    public void delete(StudentNaGodiniDTO studentNaGodini) {
        studentNaGodini.setObrisano(true);
        studentNaGodiniRepository.save(studentNaGodini.toEntity());
    }

    public void delete(Long id) {
        Optional<StudentNaGodini> optional = studentNaGodiniRepository.findById(id);
        if (optional.isPresent()) {
            StudentNaGodini studentNaGodini = optional.get();
            studentNaGodini.setObrisano(true);
            studentNaGodiniRepository.save(studentNaGodini);
        }
    }

    public void vrati(StudentNaGodiniDTO studentNaGodini) {
        studentNaGodini.setObrisano(false);
        studentNaGodiniRepository.save(studentNaGodini.toEntity());
    }

    public void vrati(Long id) {
        Optional<StudentNaGodini> optional = studentNaGodiniRepository.findById(id);
        if (optional.isPresent()) {
            StudentNaGodini studentNaGodini = optional.get();
            studentNaGodini.setObrisano(false);
            studentNaGodiniRepository.save(studentNaGodini);
        }
    }
    
    public List<StudentNaGodiniDTO> search(StudentSearchDTO dto) {
        return studentNaGodiniRepository.searchStudents(
            dto.getIme(),
            dto.getPrezime(),
            dto.getBrojIndeksa(),
            dto.getGodinaUpisa()
        ).stream().map(StudentNaGodini::toDto).collect(Collectors.toList());
    }
}