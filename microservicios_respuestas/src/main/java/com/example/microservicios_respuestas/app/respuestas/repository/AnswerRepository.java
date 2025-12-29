package com.example.microservicios_respuestas.app.respuestas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.microservicios_respuestas.app.respuestas.models.entity.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    @Query("select a from Answer a join fetch a.student s join fetch a.question q join fetch q.exam e where s.studentId = ?1 and q.exam.examId = ?2")
    public Iterable<Answer> findAnswerByStudentIdAndExamId(Long studentId, Long examId);


    @Query("select e.examId from Answer a join a.student s join a.question q join q.exam e where s.studentId = ?1 group by e.examId")
    public Iterable<Long> findExamsAnsweredByStudent(Long studentId);
}
