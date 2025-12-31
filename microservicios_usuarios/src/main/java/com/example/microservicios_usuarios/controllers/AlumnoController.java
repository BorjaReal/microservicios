package com.example.microservicios_usuarios.controllers;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.microservicios_usuarios.services.AlumnoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
//@RequestMapping("/alumnos")
public class AlumnoController extends CommonController<Student, AlumnoService> {

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> viewPhoto(@PathVariable Long id) {
        Optional<Student> studentOpt = service.findById(id);
        if (studentOpt.isEmpty() || studentOpt.get().getPhoto() == null) {
            return ResponseEntity.notFound().build();
        }

        Resource img = new ByteArrayResource(studentOpt.get().getPhoto());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(img);
    }
    
    @PostMapping("/create-with-photo")
    public ResponseEntity<?> createWithPhoto(@Valid Student student, BindingResult result, 
        @RequestParam MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            student.setPhoto(file.getBytes());
        }
        return super.create(student, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Student alumno, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors()) 
            return validate(result);

        Optional<Student> studentOpt = service.findById(id);
        if (studentOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No existe el alumno a actualizar"));
        
        Student studentDb = studentOpt.get();
        studentDb.setName(alumno.getName());
        studentDb.setLastname(alumno.getLastname());
        studentDb.setEmail(alumno.getEmail());
        Optional<Student> updatedStudent = service.save(studentDb);
        
        if (updatedStudent.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No se ha podido actualizar el alumno"));
        
        return ResponseEntity.ok(updatedStudent.get());
    }

    @PutMapping("/update-with-photo/{id}")
    public ResponseEntity<?> updateWithPhoto(@Valid Student alumno, 
        BindingResult result, @PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        if(result.hasErrors()) 
            return validate(result);

        Optional<Student> studentOpt = service.findById(id);
        if (studentOpt.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No existe el alumno a actualizar"));
        
        Student studentDb = studentOpt.get();
        studentDb.setName(alumno.getName());
        studentDb.setLastname(alumno.getLastname());
        studentDb.setEmail(alumno.getEmail());
        if(!file.isEmpty()) {
            studentDb.setPhoto(file.getBytes());
        }
        Optional<Student> updatedStudent = service.save(studentDb);
        if (updatedStudent.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No se ha podido actualizar el alumno"));
        
        return ResponseEntity.ok(updatedStudent.get());
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<?> search(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNameOrLastname(term));
    }
}
