package com.example.demo.service;

import com.example.demo.dto.IspitDTO;
import com.example.demo.dto.StudentNaGodiniDTO;
import com.example.demo.dto.StudentPregledDTO;
import com.example.demo.dto.StudentSearchDTO;
import com.example.demo.dto.UpisDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DodeljenoPravoPristupa;
import com.example.demo.model.Osoba;
import com.example.demo.model.Predmet;
import com.example.demo.model.PrijavaPolaganja;
import com.example.demo.model.Silabus;
import com.example.demo.model.Student;
import com.example.demo.model.StudentNaGodini;
import com.example.demo.repository.PrijavaPolaganjaRepository;
import com.example.demo.repository.StudentNaGodiniRepository;
import com.example.demo.saveDto.StudentNaGodiniSaveDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    
    @Autowired
    private PrijavaPolaganjaRepository prijavaPolaganjaRepository;


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
    
    public List<StudentNaGodiniDTO> search(StudentSearchDTO dto) {
        String q = dto.getQuery();
        if (q == null || q.isBlank()) return new ArrayList<>();
        return studentNaGodiniRepository.searchStudentsByQuery(q.toLowerCase())
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

    public List<StudentNaGodiniDTO> findByStudentId(Long id) {
        return ((List<StudentNaGodini>) studentNaGodiniRepository.findByStudentId(id))
                .stream()
                .map(StudentNaGodini::toDto)
                .collect(Collectors.toList());
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
        novi.setDatumUpisa(new Date());

        StudentNaGodini sn = studentNaGodiniRepository.save(novi);

        String godinaPrefix = sn.getGodinaStudija().getGodina().split("/")[0]; // "2024"
        String brojIndeksaStr = godinaPrefix + sn.getId();
        Long brojIndeksa = Long.parseLong(brojIndeksaStr);

        sn.setBrojIndeksa(brojIndeksa);

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
    
    public StudentPregledDTO getStudentPregled(Long id) {
        StudentNaGodini sng = studentNaGodiniRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("StudentNaGodini not found"));

        Student student = sng.getStudent();
        Osoba osoba = student.getOsoba();

        // 1. Osnovni podaci
        String ime = osoba.getIme();
        String prezime = osoba.getPrezime();
        String jmbg = osoba.getJmbg();
        Long brojIndeksa = sng.getBrojIndeksa();
        String studijskiProgram = sng.getGodinaStudija().getStudijskiProgram().getNaziv();

        // 2. Prijave polaganja
        List<PrijavaPolaganja> prijave = prijavaPolaganjaRepository.findByPohadjanjePredmeta_StudentNaGodini_Id(sng.getId());

        List<IspitDTO> polozeni = new ArrayList<>();
        List<IspitDTO> neuspesni = new ArrayList<>();
        double zbirOcena = 0;
        int brojPolozenih = 0;
        int ukupnoEspb = 0;

        for (PrijavaPolaganja pp : prijave) {
            if (pp.getBrojBodova() == null) continue;

            boolean polozio = pp.getBrojBodova() >= 15;
            Predmet predmet = pp.getPohadjanjePredmeta().getRealizacijaPredmeta().getPredmet();
            int espb = predmet.getEspb();

            IspitDTO ispit = new IspitDTO(
                predmet.getNaziv(),
                pp.getBrojBodova(),
                pp.getPolaganje().getRok().getNaziv(),
                pp.getDatum()
            );

            if (polozio) {
                polozeni.add(ispit);
                brojPolozenih++;
                zbirOcena += pp.getPohadjanjePredmeta().getKonacnaOcena(); // ako imaš ocenu u entitetu
                ukupnoEspb += espb;
            } else {
                neuspesni.add(ispit);
            }
        }

        double prosecnaOcena = brojPolozenih == 0 ? 0 : zbirOcena / brojPolozenih;

        // 3. Upisi
        List<StudentNaGodini> sviUpisi = studentNaGodiniRepository.findByStudentId(student.getId());
        List<UpisDTO> upisi = sviUpisi.stream()
            .map(upis -> new UpisDTO(
                upis.getGodinaStudija().getGodina(),
                upis.getDatumUpisa()
            )).collect(Collectors.toList());

        return new StudentPregledDTO(
            ime, prezime, jmbg, brojIndeksa, studijskiProgram,
            prosecnaOcena, ukupnoEspb,
            upisi, polozeni, neuspesni
        );
    }

}	