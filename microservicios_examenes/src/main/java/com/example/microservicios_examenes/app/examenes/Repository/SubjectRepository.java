package com.example.microservicios_examenes.app.examenes.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.commons_exams.exams.models.entity.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

}
