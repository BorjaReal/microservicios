package com.example.microservicios_cursos.app.cursos.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons_alumnos.alumnos.models.entity.Student;
import com.example.commons_microservicios.commons.controllers.CommonController;
import com.example.microservicios_cursos.app.cursos.models.entities.Course;
import com.example.microservicios_cursos.app.cursos.services.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController extends CommonController<Course, CursoService> {

	@GetMapping("/student/{id}")
	public ResponseEntity<?> getCursoByAlumnoId(@PathVariable Long id) {
		Optional<Course> course = this.service.findCourseByStudentId(id);
		if(course.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(course.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Course curso , @PathVariable Long id) {
		Optional<Course> courseDb = this.service.findById(id);
		if(courseDb.isEmpty())
			return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		confirmedCourse.setName(curso.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	}
	
	 @PutMapping("/{id}/asign-students")
	 public ResponseEntity<?> asignStudents(@RequestBody List<Student> studentsCourse, @PathVariable Long id){
		 Optional<Course> courseDb = this.service.findById(id);
			if(courseDb.isEmpty())
				return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		studentsCourse.forEach(s -> confirmedCourse.addStudent(s));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	 }
	 
	 @PutMapping("/{id}/remove-student")
	 public ResponseEntity<?> asignStudent(@RequestBody Student studentCourse, @PathVariable Long id){
		Optional<Course> courseDb = this.service.findById(id);
			if(courseDb.isEmpty())
				return ResponseEntity.notFound().build();
		Course confirmedCourse = courseDb.get();
		confirmedCourse.removeStudent(studentCourse);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(confirmedCourse));
	 }
 	 
}
