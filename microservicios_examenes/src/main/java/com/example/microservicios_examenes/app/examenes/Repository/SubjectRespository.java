package com.example.microservicios_examenes.app.examenes.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.commons_exams.exams.models.entity.Subject;

public interface SubjectRespository extends CrudRepository<Subject, Long> {

}
