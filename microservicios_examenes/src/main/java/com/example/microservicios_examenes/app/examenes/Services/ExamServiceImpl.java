package com.example.microservicios_examenes.app.examenes.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.commons_microservicios.commons.service.CommonServiceImpl;
import com.example.microservicios_examenes.app.examenes.Repository.ExamRepository;
import com.example.microservicios_examenes.app.examenes.Repository.SubjectRepository;
import com.example.commons_exams.exams.models.entity.Exam;
import com.example.commons_exams.exams.models.entity.Subject;

@Service
public class ExamServiceImpl extends CommonServiceImpl<Exam, ExamRepository> implements ExamService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Exam> findByNameLike(String term) {
        return commonRepository.findByNameLike(term);
    }

    @Override
    public List<Subject> findAllSubjects() {
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId);
    }
}