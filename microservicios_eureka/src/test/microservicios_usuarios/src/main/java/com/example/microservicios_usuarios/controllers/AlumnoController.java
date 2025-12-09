package com.example.microservicios_usuarios.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.commons_alumnos.alumnos.models.entity.Alumno;
import com.example.microservicios_usuarios.services.AlumnoService;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alumno alumno) {
        Optional<Alumno> alumnoDb = service.findById(id);
        if (alumnoDb.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No existe el alumno a actualizar"));
        
        Alumno alumnoToUpdate = alumnoDb.get();
        alumnoToUpdate.setName(alumno.getName());
        alumnoToUpdate.setLastname(alumno.getLastname());
        alumnoToUpdate.setEmail(alumno.getEmail());
        Optional<Alumno> updatedAlumno = service.save(alumnoToUpdate);
        if (updatedAlumno.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "No se ha podido actualizar el alumno"));
        
        return ResponseEntity.ok(updatedAlumno.get());
    }
}
