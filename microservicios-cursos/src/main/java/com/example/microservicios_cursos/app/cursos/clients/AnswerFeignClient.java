package com.example.microservicios_cursos.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Feign client to get the exams answered by a student
 * @FeignClient(name = "microservicios-respuestas") is used to specify the name 
 * of the microservice that we want to call and the path of the endpoint that we want to call
 * 
 * @GetMapping("/student/{studentId}/exams-answered") 
 * is used to specify the path of the endpoint that we want 
 * to call and the method that we want to use (GET, POST, PUT, DELETE).
 */

@FeignClient(name = "microservicios-respuestas")
public interface AnswerFeignClient {
    @GetMapping("/student/{studentId}/exams-answered")
    public Iterable<Long> findExamsAnsweredByStudent(@PathVariable Long studentId);
}