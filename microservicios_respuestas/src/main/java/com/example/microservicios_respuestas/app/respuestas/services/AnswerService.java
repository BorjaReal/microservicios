package com.example.microservicios_respuestas.app.respuestas.services;

import com.example.microservicios_respuestas.app.respuestas.models.entity.Answer;

public interface AnswerService  {

    public Iterable<Answer> SaveAll(Iterable<Answer> respuestas);

    public Iterable<Answer> findRespuestaByStudentIdByExamenId(Long studentId, Long examenId);

    public Iterable<Long> findExamsAnsweredByStudent(Long studentId);
}
