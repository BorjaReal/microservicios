package com.example.microservicios_usuarios.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.example.commons_alumnos.alumnos.models.entity.Alumno;


public interface AlumnoRepository extends CrudRepository<Alumno, Long> {

}
