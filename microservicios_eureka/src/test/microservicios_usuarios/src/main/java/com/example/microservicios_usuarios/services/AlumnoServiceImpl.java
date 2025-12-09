package com.example.microservicios_usuarios.services;

import org.springframework.stereotype.Service;

import com.example.commons_alumnos.alumnos.models.entity.Alumno;
import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_usuarios.Repositories.AlumnoRepository;


@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

}