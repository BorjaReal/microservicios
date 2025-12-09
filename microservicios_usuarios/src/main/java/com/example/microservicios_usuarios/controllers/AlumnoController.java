package com.example.microservicios_usuarios.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.microservicios_usuarios.services.AlumnoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends CommonController<Student, AlumnoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student alumno) {
        Optional<Student> studentDb = service.findById(id);
        if (studentDb.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No existe el alumno a actualizar"));
        
        Student studentToUpdate = studentDb.get();
        studentToUpdate.setName(alumno.getName());
        studentToUpdate.setLastname(alumno.getLastname());
        studentToUpdate.setEmail(alumno.getEmail());
        Optional<Student> updatedStudent = service.save(studentToUpdate);
        if (updatedStudent.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No se ha podido actualizar el alumno"));
        
        return ResponseEntity.ok(updatedStudent.get());
    }


    @GetMapping("/search/{term}")
    public ResponseEntity<?> search(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNameOrLastname(term));
    }
}
