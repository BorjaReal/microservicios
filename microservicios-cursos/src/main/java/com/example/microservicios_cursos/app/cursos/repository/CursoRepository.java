package com.example.microservicios_cursos.app.cursos.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.microservicios_cursos.app.cursos.models.entities.Course;


public interface CursoRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c join fetch c.students s where s.id = ?1")
    public Optional<Course> findCourseByStudentId(Long alumnoId);
}
