package com.example.microservicios_respuestas.app.respuestas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservicios_respuestas.app.respuestas.models.entity.Answer;
import com.example.microservicios_respuestas.app.respuestas.services.AnswerService;

@RestController
public class RespuestaContoller {

    @Autowired
    private AnswerService service;

    @GetMapping("/student/{studentId}/exam/{examId}")
    public ResponseEntity<?> getAnswerByStudentAndExam(@PathVariable Long studentId, @PathVariable Long examId) {
        Iterable<Answer> answers = service.findRespuestaByStudentIdByExamenId(studentId, examId);
        if(answers.iterator().hasNext()) 
            return ResponseEntity.ok(answers);
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron respuestas para el estudiante con ID: " + studentId + " y el examen con ID: " + examId); 
    }

    @GetMapping("/student/{studentId}/exams-answered")
    public ResponseEntity<?> getExamsWithAnswersByStudent(@PathVariable Long studentId) {
        
        /** Devuelve los exámenes con respuestas para el alumno */
        Iterable<Long> examIds = service.findExamsAnsweredByStudent(studentId);
        
        
        if(examIds.iterator().hasNext()) 
            return ResponseEntity.ok(examIds);
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron exámenes con respuestas para el estudiante con ID: " + studentId);
    }

    @PostMapping
    public ResponseEntity<?> crearRespuestas(@RequestBody Iterable<Answer> respuestas) {
        Iterable<Answer> respuestasGuardadas = service.SaveAll(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestasGuardadas);
    }

}
