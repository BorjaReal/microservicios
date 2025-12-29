package com.example.microservicios_cursos.app.cursos.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.microservicios_cursos.app.cursos.models.entities.Course;


public interface CursoRepository extends PagingAndSortingRepository<Course, Long> {

    /***
     * 
     * @param alumnoId
     * @return Course from the student by id
     */
    @Query("select c from Course c join fetch c.students s where s.id = ?1")
    public Optional<Course> findCourseByStudentId(Long alumnoId);
}
