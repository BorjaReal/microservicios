package com.example.microservicios_examenes.app.examenes.Controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.microservicios_examenes.app.examenes.Services.ExamService;
import com.example.commons_exams.exams.models.entity.Exam;
import com.example.commons_exams.exams.models.entity.Question;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
public class ExamController extends CommonController<Exam, ExamService> {
    @GetMapping("/search/{term}")
    public ResponseEntity<?> searchByName(@PathVariable String term) {
        return ResponseEntity.ok(service.findByNameLike(term));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id) {
        if(result.hasErrors())
            return validate(result);
        
        Optional<Exam> examDb = service.findById(id);
        if (examDb.isEmpty())
            return ResponseEntity.notFound().build();
        
        Exam confirmedExamDb = examDb.get();

        Set<Question> questionsToRemove = confirmedExamDb.getQuestions().stream()
            .filter(oldQuestion -> !exam.getQuestions().contains(oldQuestion))
            .collect(Collectors.toSet());
    
        Set<Question> questionsToAdd = exam.getQuestions().stream()
            .filter(newQuestions -> !confirmedExamDb.getQuestions().contains(newQuestions))
            .collect(Collectors.toSet());

        questionsToRemove.forEach(confirmedExamDb::removeQuestion);
        questionsToAdd.forEach(confirmedExamDb::addQuestion);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(confirmedExamDb));
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok(service.findAllSubjects());
    }
}