package com.example.microservicios_cursos.app.cursos.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_cursos.app.cursos.models.entities.Course;
import com.example.microservicios_cursos.app.cursos.repository.CursoRepository;


@Service
public class CursoServiceImpl extends CommonServiceImpl<Course, CursoRepository> implements CursoService {
    @Override
    public Optional<Course> findCourseByStudentId(Long studentId) {
        return commonRepository.findCourseByStudentId(studentId);
    }
}
