package com.example.commons_microservicios.commons.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.commons_microservicios.commons.service.CommonService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class CommonController<E, S extends CommonService<E>> {

    @Autowired
    protected S service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<?> alumno = service.findById(id);
        return alumno.isPresent() ? 
            ResponseEntity.ok(alumno.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody E entity) {
        Optional<E> savedAlumno = service.save(entity);
        if (savedAlumno.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlumno.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<E> existingAlumno = service.findById(id);
        if (existingAlumno.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El elemento a borrar no existe"));
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
