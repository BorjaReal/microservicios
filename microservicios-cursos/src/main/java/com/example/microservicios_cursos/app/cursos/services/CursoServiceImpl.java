package com.example.microservicios_cursos.app.cursos.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_cursos.app.cursos.clients.AnswerFeignClient;
import com.example.microservicios_cursos.app.cursos.models.entities.Course;
import com.example.microservicios_cursos.app.cursos.repository.CursoRepository;


@Service
public class CursoServiceImpl extends CommonServiceImpl<Course, CursoRepository> implements CursoService {
    
    @Autowired
    private AnswerFeignClient client;
    
    @Override
    public Optional<Course> findCourseByStudentId(Long studentId) {
        return commonRepository.findCourseByStudentId(studentId);
    }

    @Override
    public Iterable<Long> findExamsAnsweredByStudent(Long studentId) {
        return client.findExamsAnsweredByStudent(studentId);
    }
}
