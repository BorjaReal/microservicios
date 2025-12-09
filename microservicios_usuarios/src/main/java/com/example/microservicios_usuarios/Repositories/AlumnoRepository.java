package com.example.microservicios_usuarios.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.commons_alumnos.alumnos.models.entity.Student;


public interface AlumnoRepository extends CrudRepository<Student, Long> {

    @Query("select s from Student s where s.name like %?1% or s.lastname like %?1%")
    public List<Student> findByNameOrLastname(String search);
}
