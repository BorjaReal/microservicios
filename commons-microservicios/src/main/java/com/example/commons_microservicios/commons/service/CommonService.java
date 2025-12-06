package com.example.commons_microservicios.commons.service;

import java.util.Optional;


public interface CommonService<E> {

    public Iterable<E> findAll();

    public Optional<E> findById(Long id);

    public Optional<E> save(E alumno);

    public void deleteById(Long id);
}
