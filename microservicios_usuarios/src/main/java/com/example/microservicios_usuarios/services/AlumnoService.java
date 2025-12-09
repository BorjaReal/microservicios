package com.example.microservicios_usuarios.services;


import java.util.List;

import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.commons_microservicios.commons.service.CommonService;

public interface AlumnoService extends CommonService<Student> {

    public List<Student> findByNameOrLastname(String search);
}
