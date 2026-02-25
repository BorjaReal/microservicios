package com.example.commons_microservicios.commons.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

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

    @GetMapping("/pageable")
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findPage(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<?> entity = service.findById(id);
        return entity.isPresent() ? ResponseEntity.ok(entity.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody E entity, BindingResult result) {
        if(result.hasErrors()) {
            return validate(result);
        }
        Optional<E> savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<E> entityFromDB = service.findById(id);
        if (entityFromDB.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "El elemento a borrar no existe"));
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    protected ResponseEntity<?> validate(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> 
            errors.put(err.getField(), "El campo '" + err.getField() + "' " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
