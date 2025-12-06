package com.example.microservicios_cursos.app.cursos.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons_alumnos.alumnos.models.entity.Alumno;
import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.microservicios_cursos.app.cursos.models.entities.Curso;
import com.example.microservicios_cursos.app.cursos.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController  extends CommonController<Curso, CursoService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso , @PathVariable Long id) {
		Optional<Curso> cursoDb = this.service.findById(id);
		if(cursoDb.isEmpty())
			return ResponseEntity.notFound().build();
		Curso cursoConfirmado = cursoDb.get();
		cursoConfirmado.setName(curso.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoConfirmado));
	}
	
	 @PutMapping("/{id}/asign-students")
	 public ResponseEntity<?> asignStudents(@RequestBody List<Alumno> studentsCourse, @PathVariable Long id){
		 Optional<Curso> cursoDb = this.service.findById(id);
			if(cursoDb.isEmpty())
				return ResponseEntity.notFound().build();
		Curso cursoConfirmado = cursoDb.get();
		studentsCourse.forEach(s -> cursoConfirmado.addStudent(s));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoConfirmado));
	 }
	 
	 @PutMapping("/{id}/remove-student")
	 public ResponseEntity<?> asignStudent(@RequestBody Alumno studentCourse, @PathVariable Long id){
		Optional<Curso> cursoDb = this.service.findById(id);
			if(cursoDb.isEmpty())
				return ResponseEntity.notFound().build();
		Curso cursoConfirmado = cursoDb.get();
		cursoConfirmado.removeStudent(studentCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(cursoConfirmado));
	 }
	 
}
