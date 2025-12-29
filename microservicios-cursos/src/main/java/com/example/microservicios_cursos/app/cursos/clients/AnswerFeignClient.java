package com.example.microservicios_cursos.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicios-respuestas")
public interface AnswerFeignClient {
    @GetMapping("/student/{studentId}/exams-answered")
    public Iterable<Long> findExamsAnsweredByStudent(@PathVariable Long studentId);
}
