package com.example.microservicios_examenes.app.examenes.Services;

import com.example.commons_microservicios.commons.service.CommonService;

import java.util.List;
import java.util.Optional;

import com.example.commons_exams.exams.models.entity.Subject;

import com.example.commons_exams.exams.models.entity.Exam;

public interface ExamService extends CommonService<Exam>{
    public List<Exam> findByNameLike(String term);

    public List<Subject> findAllSubjects();

    public Optional<Subject> findSubjectById(Long subjectId);
}