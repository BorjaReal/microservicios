package com.example.microservicios_respuestas.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microservicios_respuestas.app.respuestas.models.entity.Answer;
import com.example.microservicios_respuestas.app.respuestas.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService {
    
    @Autowired
    private AnswerRepository repository;

    @Override
    @Transactional
    public Iterable<Answer> SaveAll(Iterable<Answer> respuestas) {
        return repository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Answer> findRespuestaByStudentIdByExamenId(Long studentId, Long examenId) {
        return repository.findAnswerByStudentIdAndExamId(studentId, examenId);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Long> findExamsAnsweredByStudent(Long studentId) {
        return repository.findExamsAnsweredByStudent(studentId);
    }
}
