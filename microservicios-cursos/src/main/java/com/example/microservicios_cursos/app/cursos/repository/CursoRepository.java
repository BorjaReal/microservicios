package com.example.microservicios_cursos.app.cursos.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.microservicios_cursos.app.cursos.models.entities.Curso;


public interface CursoRepository extends CrudRepository<Curso, Long> {

}
