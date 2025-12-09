package com.example.commons_microservicios.commons.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonService<E> {

    @Autowired
    protected R commonRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return commonRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return commonRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<E> save(E alumno) {
       return Optional.of(commonRepository.save(alumno));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    	commonRepository.deleteById(id);
        
    }
}