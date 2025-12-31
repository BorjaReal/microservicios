package com.example.microservicios_cursos.app.cursos.services;

import java.util.Optional;

import com.example.commons_microservicios.commons.service.CommonService;
import com.example.microservicios_cursos.app.cursos.models.entities.Course;


public interface CursoService extends CommonService<Course> {

    /***
     * 
     * @param alumnoId
     * @return Course from the student by id
     */
    public Optional<Course> findCourseByStudentId(Long alumnoId);

    public Iterable<Long> findExamsAnsweredByStudent(Long studentId);
}