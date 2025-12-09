package com.example.microservicios_usuarios.services;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_usuarios.Repositories.AlumnoRepository;


@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Student, AlumnoRepository> implements AlumnoService {

    @Override
    @Transactional(readOnly = true)
    public List<Student> findByNameOrLastname(String search) {
       return commonRepository.findByNameOrLastname(search);
    }
}